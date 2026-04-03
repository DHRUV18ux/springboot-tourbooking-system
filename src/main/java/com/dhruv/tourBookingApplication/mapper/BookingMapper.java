package com.dhruv.tourBookingApplication.mapper;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;
import com.dhruv.tourBookingApplication.dto.response.TourSummaryResponseDto;
import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.entites.Booking;
import com.dhruv.tourBookingApplication.entites.Tour;
import com.dhruv.tourBookingApplication.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    private final TourMapper tourMapper;

    @Autowired
    public BookingMapper(TourMapper tourMapper) {
        this.tourMapper = tourMapper;
    }


    public BookingResponseDto convertToBookingResponseDto(Booking booking){
        User user=booking.getCustomer();
        UserResponseDto userResponseDto=UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contactNo(user.getContactNo())
                .role(user.getRole())
                .build();

        Tour tour=booking.getTour();
        TourSummaryResponseDto tourSummaryResponseDto =tourMapper.convertToTourSummaryResponseDto(tour);

        BookingResponseDto bookingResponseDto=BookingResponseDto.builder()
                .bookingId(booking.getBookingId())
                .customer(userResponseDto)
                .tour(tourSummaryResponseDto)
                .numberOfTickets(booking.getNumberOfTickets())
                .bookingDate(booking.getBookingDate())
                .paymentStatus(booking.getPaymentStatus())
                .totalPrice(booking.getTotalPrice())
                .stripeSessionUrl(booking.getStripeSessionUrl())
                .paymentTransactionId(booking.getPaymentTransactionId())
                .build();
        return  bookingResponseDto;
    }
}
