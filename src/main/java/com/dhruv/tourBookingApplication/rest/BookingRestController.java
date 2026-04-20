package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.BookingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.BookingResponseDto;
import com.dhruv.tourBookingApplication.entites.Booking;
import com.dhruv.tourBookingApplication.service.interfaces.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Customer - Bookings",
        description = "APIs for customers to create new bookings and view their own bookings"
)
public class BookingRestController {
      private final BookingService bookingService;

       @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     @PostMapping
     @Operation(
             summary = "create a  new booking",
             description = "Authenticated customer can book a tour by booking details by BookingRequestDto." +
                     "Email is extracted from the JWT token"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200",description = "Booking created successfully"),
             @ApiResponse(responseCode = "400", description = "Invalid input data or insufficient tickets"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "402", description = "Payment failed - Stripe session creation failed"),
             @ApiResponse(responseCode = "403", description = "Access denied - Customer only"),
             @ApiResponse(responseCode = "404", description = "Tour not found or User not found"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
     public ResponseEntity<BookingResponseDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto, @AuthenticationPrincipal UserDetails userDetails){
               String email=userDetails.getUsername();
              BookingResponseDto responseDto=bookingService.addBooking(bookingRequestDto,email);
              return new ResponseEntity<BookingResponseDto>(responseDto, HttpStatus.CREATED);
     }

      @GetMapping("/my")
      @Operation(
              summary = "Get my bookings",
              description = "Returns all bookings of the currently logged in customer. Email is extracted from JWT token automatically"
      )
      @ApiResponses(value = {
              @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
              @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
              @ApiResponse(responseCode = "403", description = "Access denied - Customer only"),
              @ApiResponse(responseCode = "404", description = "No bookings found for this customer"),
              @ApiResponse(responseCode = "500", description = "Internal server error")
      })
     public ResponseEntity<List<BookingResponseDto>> getMyBookings(@AuthenticationPrincipal UserDetails userDetails){
            String email=userDetails.getUsername();
           List<BookingResponseDto> responseDtoList=bookingService.getMyBookings(email);
           return new ResponseEntity<List<BookingResponseDto>>(responseDtoList,HttpStatus.OK);
     }


}
