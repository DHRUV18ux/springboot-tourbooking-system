package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.service.interfaces.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
@Tag(
        name = "Stripe Webhook",
        description = "Webhook endpoint for Stripe to notify payment status updates. This is called by Stripe automatically, not by the client"
)
public class StripeWebhookController {

    private final BookingService bookingService;

     @Autowired
    public StripeWebhookController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     @PostMapping
     @Operation(
             summary = "Handle Stripe webhook",
             description = "Receives payment events from Stripe and updates booking payment status accordingly. This endpoint is called by Stripe server automatically after payment success or failure"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Webhook received and processed successfully"),
             @ApiResponse(responseCode = "400", description = "Invalid payload or Stripe signature verification failed"),
             @ApiResponse(responseCode = "500", description = "Internal server error while processing webhook")
     })
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
         bookingService.handleStripeWebhook(payload,sigHeader);
         return new ResponseEntity<String>("Webhook received", HttpStatus.OK);
    }

}
