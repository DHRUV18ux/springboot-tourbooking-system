package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/bookings")
@Tag(
    name="Admin Booking Api",
     description = "This Controllers is for the admin to view all the booking that have made" +
             "And find all the Booking by Tours"
)
public class AdminBookingController {

    private final BookingService bookingService;

    @Autowired
    public AdminBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     @GetMapping
     @Operation(
             summary ="Get all bookings",
             description = "Returns a list of all bookings made by all customers"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
     public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
           List<BookingResponseDto> responseDtoList=bookingService.getAllBookings();
           return new ResponseEntity<List<BookingResponseDto>>(responseDtoList, HttpStatus.OK);
     }

     @GetMapping("/tour/{tourId}")
     @Operation(
             summary = "Get bookings by tour",
             description = "Returns all bookings made for a specific tour by its TourId"
     )
     @ApiResponses(value={
             @ApiResponse(responseCode ="200",description = "Booking retrieved successfully"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode="403",description = "Access denied  Admin only"),
             @ApiResponse(responseCode = "404", description = "No bookings found for given tour ID"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     }
     )
    public ResponseEntity<List<BookingResponseDto>> getAllBookingByTour(@PathVariable Long tourId){
         List<BookingResponseDto>responseDtoList=bookingService.getBookingByTour(tourId);
         return new ResponseEntity<List<BookingResponseDto>>(responseDtoList,HttpStatus.OK);
     }



}
