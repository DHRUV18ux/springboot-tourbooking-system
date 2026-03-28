package com.dhruv.tourBookingApplication.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(String message){
        super(message);
    }
}
