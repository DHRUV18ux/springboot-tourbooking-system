package com.dhruv.tourBookingApplication.service.interfaces;

import com.dhruv.tourBookingApplication.dto.request.BookingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto addBooking(BookingRequestDto bookingRequestDto,String email);

    List<BookingResponseDto> getAllBookings();

    List<BookingResponseDto> getMyBookings(String userEmail);

    List<BookingResponseDto> getBookingByTour(Long tourId);

    void handleStripeWebhook(String payload, String sigHeader);
}