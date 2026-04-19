package com.dhruv.tourBookingApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Helper method
      private ResponseEntity<ErrorResponseDto>builder(int status, String message, HttpStatus httpStatus){
          ErrorResponseDto error=new ErrorResponseDto(status,message, LocalDateTime.now());
          return new ResponseEntity<ErrorResponseDto>(error,httpStatus);
      }

    // ─── 404 NOT FOUND ───────────────────────────────────────────
     @ExceptionHandler(BookingNotFoundException.class)
     public ResponseEntity<ErrorResponseDto>handleBookingNotFound(BookingNotFoundException ex){
        return builder(404,ex.getMessage(),HttpStatus.NOT_FOUND);
     }

       @ExceptionHandler(LocationNotFoundException.class)
       public ResponseEntity<ErrorResponseDto>handleLocationNotFound(LocationNotFoundException ex){
          return builder(404,ex.getMessage(),HttpStatus.NOT_FOUND);
     }
      @ExceptionHandler(LodgingNotFoundException.class)
     public ResponseEntity<ErrorResponseDto>handleLodgingNotFound(LodgingNotFoundException ex){
          return builder(404,ex.getMessage(),HttpStatus.NOT_FOUND);
     }
      @ExceptionHandler(TransportNotFoundException.class)
      public ResponseEntity<ErrorResponseDto>handleTransportNotFound(TransportNotFoundException ex){
          return builder(404, ex.getMessage(), HttpStatus.NOT_FOUND);
      }
       @ExceptionHandler(TourNotFoundException.class)
     public ResponseEntity<ErrorResponseDto>handleTourNotFound(TourNotFoundException ex){
        return builder(404, ex.getMessage(), HttpStatus.NOT_FOUND);
    }
     @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>handleUserNotFound(UserNotFoundException ex){
          return builder(404,ex.getMessage(),HttpStatus.NOT_FOUND);
     }

    // ─── 409 CONFLICT ────────────────────────────────────────────
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto>handleUserAlreadyExists(UserAlreadyExistsException ex){
          return builder(409, ex.getMessage(), HttpStatus.CONFLICT);
    }
    // ─── 400 BAD REQUEST ─────────────────────────────────────────
    @ExceptionHandler(InvalidTourDateException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidTourDate(InvalidTourDateException ex) {
        return builder(400, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InSufficientTicketsException.class)
    public ResponseEntity<ErrorResponseDto> handleInsufficientTickets(InSufficientTicketsException ex) {
        return builder(400, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    // ─── 402 PAYMENT FAILED ──────────────────────────────────────
    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ErrorResponseDto> handlePaymentFailed(PaymentFailedException ex) {
        return builder(402, ex.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
    // ─── 400 VALIDATION (@Valid) ─────────────────────────────────
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleValidationErrors(MethodArgumentNotValidException ex){
          Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    // ─── 500 FALLBACK ─────────────────────────────────────────────
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        return builder(500, "Something went wrong: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
