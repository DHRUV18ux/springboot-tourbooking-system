package com.dhruv.tourBookingApplication.entites;

import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private Integer numberOfTickets;
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    private boolean isBookingConfirmed;
    private  String paymentTransactionId;

}
