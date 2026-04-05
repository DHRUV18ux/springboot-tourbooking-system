package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.BookingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;
import com.dhruv.tourBookingApplication.entites.Booking;
import com.dhruv.tourBookingApplication.entites.Tour;
import com.dhruv.tourBookingApplication.entites.User;
import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import com.dhruv.tourBookingApplication.exception.*;
import com.dhruv.tourBookingApplication.mapper.BookingMapper;
import com.dhruv.tourBookingApplication.repo.BookingRepo;
import com.dhruv.tourBookingApplication.repo.TourRepo;
import com.dhruv.tourBookingApplication.repo.UserRepo;
import com.dhruv.tourBookingApplication.service.interfaces.BookingService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final TourRepo tourRepo;
    private final UserRepo userRepo;
    private final BookingMapper bookingMapper;

    @Value("${stripe.secret.key}")
    private String stripeApiKey;

    @Value("${app.frontend.success-url}")
    private String successUrl;

    @Value("${app.frontend.cancel-url}")
    private String cancelUrl;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

     @Autowired
    public BookingServiceImpl(BookingRepo bookingRepo, TourRepo tourRepo, UserRepo userRepo, BookingMapper bookingMapper){
        this.bookingRepo=bookingRepo;
        this.tourRepo=tourRepo;
        this.userRepo=userRepo;
         this.bookingMapper = bookingMapper;
     }

      private String createStripeSessionUrl(Tour tour,Integer numberOfTickets,Double totalPrice,String userEmail)  {
             try {
                 // Set your Stripe API key
                 Stripe.apiKey = stripeApiKey;
                 // metadata is extra info you attach to the session
                 // Stripe sends this back in the webhook
                 // so you know which tour and user the payment is for
                 Map<String, String> metadata = new HashMap<>();
                 metadata.put("tourId", tour.getId().toString());
                 metadata.put("userEmail", userEmail);
                 metadata.put("numberOfTickets", numberOfTickets.toString());
                 //Build the Stripe CheckOutSession
                 SessionCreateParams createParams = SessionCreateParams.builder()
                         .setMode(SessionCreateParams.Mode.PAYMENT)
                         .setCustomerEmail(userEmail)
                         //where to Redirect after successfully payment
                         .setSuccessUrl(successUrl + "?session_id={CHECKOUT_SESSION_ID}")
                         .setCancelUrl(cancelUrl)
                         .addLineItem(SessionCreateParams.LineItem.builder()
                                 .setQuantity(Long.valueOf(numberOfTickets))
                                 .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                         .setCurrency("inr")
                                         .setUnitAmount((long) (tour.getPrice() * 100))
                                         .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                 .setName("Tour Booking :" + tour.getTourName())
                                                 .setDescription(tour.getTourDescription())
                                                 .build()
                                         )
                                         .build()
                                 )
                                 .build()
                         )
                         .putAllMetadata(metadata)
                         .build();
                 Session session = Session.create(createParams);
                 return session.getUrl();
             }
             catch (StripeException e){
                 throw new PaymentFailedException( "Failed to create payment session: " + e.getMessage());
             }
      }

    @Override
    public BookingResponseDto addBooking(BookingRequestDto bookingRequestDto,String email) {
        Optional<Tour>isTour=tourRepo.findById(bookingRequestDto.getTourId());
        Tour tour=null;
        if(isTour.isPresent()){
             tour=isTour.get();
        }
        else{
            throw new TourNotFoundException("Tour does not exists with  this tour id :"+bookingRequestDto.getTourId());
        }

        if(tour.getTicketAvailable()<bookingRequestDto.getNumberOfTickets()){
            throw new InSufficientTicketsException("Only "+tour.getTicketAvailable()+" tickets available for this tour");
        }

        Optional<User>isUser=userRepo.findByEmail(email);
        User user=null;
        if(isUser.isPresent()){
             user=isUser.get();
        }
        else{
            throw new UserNotFoundException("User not found with the email "+email);
        }

        Double totalPrice=bookingRequestDto.getNumberOfTickets()*tour.getPrice();

        //create Stripe checkout session
        String stripeSessionUrl=createStripeSessionUrl(tour,bookingRequestDto.getNumberOfTickets(),totalPrice,email);

        Booking booking=Booking.builder()
                .customer(user)
                .tour(tour)
                .numberOfTickets(bookingRequestDto.getNumberOfTickets())
                .totalPrice(totalPrice)
                .paymentStatus(PaymentStatus.PENDING)
                .bookingDate(LocalDate.now())
                .stripeSessionUrl(stripeSessionUrl)
                .build();
        Booking savedBooking=bookingRepo.save(booking);
        return bookingMapper.convertToBookingResponseDto(savedBooking);
    }

    @Override
    public void handleStripeWebhook(String payload, String sigHeader) {
         try{
             // Step 1 - verify the request is genuinely from Stripe
             // If signature is wrong this throws an exception
             Event event= Webhook.constructEvent(payload,sigHeader,webhookSecret);
             // Step 2 - check what type of event this is
             // We only care about successful payments
             if("checkout.session.completed".equals(event.getType())){
                 // Step 3 - get the session data

                 Session session = ApiResource.GSON.fromJson(
                         event.getDataObjectDeserializer().getRawJson(),
                         Session.class
                 );
                 // Step 4 - get metadata we attached when creating session
                 Map<String,String>metadata=session.getMetadata();
                 Long tourId=Long.parseLong(metadata.get("tourId"));
                 String userEmail=metadata.get("userEmail");
                 Integer numberOfTickets=Integer.parseInt(metadata.get("numberOfTickets"));
                 // Step 5 - find the pending booking
                 Booking booking=bookingRepo.findByTour_IdAndCustomer_EmailAndPaymentStatus(tourId,userEmail,PaymentStatus.PENDING)
                         .orElseThrow(()->new BookingNotFoundException("pending booking not found"));
                 // Step 6 - update booking status to SUCCESS
                 booking.setPaymentStatus(PaymentStatus.PAID);
                 booking.setPaymentTransactionId(session.getPaymentIntent());
                 bookingRepo.save(booking);
                 Tour tour=booking.getTour();
                 tour.setTicketAvailable(tour.getTicketAvailable()-numberOfTickets);
                 tour.setTicketsSold(tour.getTicketsSold()+numberOfTickets);
                 tourRepo.save(tour);
             }
         }
         catch (Exception e){
             throw new PaymentFailedException("Webhook processing failed: " + e.getMessage());
         }
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        List<Booking>allBookings=bookingRepo.findAll();
        List<BookingResponseDto>responseDtoList=new ArrayList<>();
        for(Booking booking:allBookings){
            responseDtoList.add(bookingMapper.convertToBookingResponseDto(booking));
        }
        return responseDtoList;
    }

    @Override
    public List<BookingResponseDto> getMyBookings(String userEmail) {
         Optional<User>isUser=userRepo.findByEmail(userEmail);
         User user=null;
         if(isUser.isPresent()){
               user=isUser.get();
         }
         else{
             throw new UserNotFoundException("user not found with the email"+userEmail);
         }
         List<Booking>bookings=bookingRepo.findByCustomer(user);
         List<BookingResponseDto>responseDtoList=new ArrayList<>();
         for(Booking booking:bookings){
             responseDtoList.add(bookingMapper.convertToBookingResponseDto(booking));
         }
         return responseDtoList;
    }

    @Override
    public List<BookingResponseDto> getBookingByTour(Long tourId) {
        Optional<Tour>isTour=tourRepo.findById(tourId);
        Tour tour=null;
        if(isTour.isPresent()){
            tour=isTour.get();
        }
        else{
            throw new TourNotFoundException("Tour not found with the id :"+tourId);
        }
        List<Booking>bookings=bookingRepo.findByTour(tour);
        List<BookingResponseDto>responseDtoList=new ArrayList<>();
        for(Booking booking:bookings){
            responseDtoList.add(bookingMapper.convertToBookingResponseDto(booking));
        }
        return responseDtoList;
    }
}
