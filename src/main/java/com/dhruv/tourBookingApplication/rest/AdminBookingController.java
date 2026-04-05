package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {

    private final BookingService bookingService;

    @Autowired
    public AdminBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     @GetMapping
     public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
           List<BookingResponseDto> responseDtoList=bookingService.getAllBookings();
           return new ResponseEntity<List<BookingResponseDto>>(responseDtoList, HttpStatus.OK);
     }

     @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<BookingResponseDto>> getAllBookingByTour(@PathVariable Long tourId){
         List<BookingResponseDto>responseDtoList=bookingService.getBookingByTour(tourId);
         return new ResponseEntity<List<BookingResponseDto>>(responseDtoList,HttpStatus.OK);
     }



}
