package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.entites.Booking;
import com.dhruv.tourBookingApplication.entites.Tour;
import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import com.dhruv.tourBookingApplication.repo.BookingRepo;
import com.dhruv.tourBookingApplication.repo.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class BookingSchedular {
    private final BookingRepo bookingRepo;
    private final TourRepo tourRepo;

     @Autowired
    public BookingSchedular(BookingRepo bookingRepo, TourRepo tourRepo) {
        this.bookingRepo = bookingRepo;
        this.tourRepo = tourRepo;
    }
     @Scheduled(fixedRate=900_000)
     @Transactional
    public void releaseExpiredBooking(){
         System.out.println("Schedular Running at :"+ LocalDate.now());
        LocalDateTime cutoffTime=LocalDateTime.now().minusMinutes(30);
        List<Booking> expiredBookings=bookingRepo.findByPaymentStatusAndCreatedAtBefore(PaymentStatus.PENDING,cutoffTime);
        if (expiredBookings.isEmpty()) {
            System.out.println("[Scheduler] No expired bookings found.");
            return;
        }
        System.out.println("[Scheduler] Found "
                + expiredBookings.size() + " expired bookings.");
        for(Booking booking:expiredBookings){
            booking.setPaymentStatus(PaymentStatus.EXPIRED);
            bookingRepo.save(booking);
            Tour tour=booking.getTour();
            tour.setTicketAvailable(
                    tour.getTicketAvailable() + booking.getNumberOfTickets());
            tourRepo.save(tour);
            System.out.println("[Scheduler] Expired booking ID: "
                    + booking.getBookingId()
                    + " | Restored " + booking.getNumberOfTickets()
                    + " tickets to: " + tour.getTourName());
        }
        System.out.println("[Scheduler] Done processing expired bookings.");
    }

}
