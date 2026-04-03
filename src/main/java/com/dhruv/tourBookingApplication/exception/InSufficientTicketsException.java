package com.dhruv.tourBookingApplication.exception;

public class InSufficientTicketsException extends RuntimeException {
    public InSufficientTicketsException(String message) {

        super(message);
    }
}
