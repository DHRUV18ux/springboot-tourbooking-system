package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.BookingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;
import com.dhruv.tourBookingApplication.entites.Booking;
import com.dhruv.tourBookingApplication.service.interfaces.BookingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// this is the controller for user request
@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {
      private final BookingService bookingService;

       @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     @PostMapping
     public ResponseEntity<BookingResponseDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto, @AuthenticationPrincipal UserDetails userDetails){
               String email=userDetails.getUsername();
              BookingResponseDto responseDto=bookingService.addBooking(bookingRequestDto,email);
              return new ResponseEntity<BookingResponseDto>(responseDto, HttpStatus.CREATED);
     }

      @GetMapping("/my")
     public ResponseEntity<List<BookingResponseDto>> getMyBookings(@AuthenticationPrincipal UserDetails userDetails){
            String email=userDetails.getUsername();
           List<BookingResponseDto> responseDtoList=bookingService.getMyBookings(email);
           return new ResponseEntity<List<BookingResponseDto>>(responseDtoList,HttpStatus.OK);
     }


}
