package com.dhruv.tourBookingApplication.exception;

public class InvalidTourDateException extends RuntimeException {
    public InvalidTourDateException(String msg){
        super(msg);
    }
}
