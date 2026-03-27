package com.dhruv.tourBookingApplication.entites;

import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


import java.time.LocalDate;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"customer","tour"})
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name="tour_id",referencedColumnName = "id",nullable = false)
    private Tour tour;

    @NotNull(message = "Number of Tickets are required")
    @Positive(message = "Number of Tickets must be greater than 0")
    private Integer numberOfTickets;

    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be greater than 0")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    private boolean isBookingConfirmed;
    private  String paymentTransactionId;

}
