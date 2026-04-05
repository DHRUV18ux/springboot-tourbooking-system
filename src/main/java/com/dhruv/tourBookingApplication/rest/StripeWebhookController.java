package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.service.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
public class StripeWebhookController {

    private final BookingService bookingService;

     @Autowired
    public StripeWebhookController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
     @PostMapping
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
         bookingService.handleStripeWebhook(payload,sigHeader);
         return new ResponseEntity<String>("Webhook received", HttpStatus.OK);
    }

}
