package com.dhruv.tourBookingApplication.dto.response;

import com.dhruv.tourBookingApplication.dto.request.UserRegisterDto;
import com.dhruv.tourBookingApplication.entites.Tour;
import com.dhruv.tourBookingApplication.entites.User;
import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import lombok.*;


import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {
    private Long bookingId;
    private UserResponseDto customer;
    private TourSummaryResponseDto tour;
    private Integer numberOfTickets;
    private Double totalPrice;
    private PaymentStatus paymentStatus;
    private LocalDate bookingDate;
    private String stripeSessionUrl;
    private  String paymentTransactionId;
}
