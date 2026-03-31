package com.dhruv.tourBookingApplication.exception;

public class LodgingNotFoundException extends RuntimeException {
    public LodgingNotFoundException(String message) {
        super(message);
    }
}
