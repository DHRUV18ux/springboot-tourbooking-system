package com.dhruv.tourBookingApplication.entites;

import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"customer","tour"})
@Table(name="bookings")
@Builder
// @EntityListeners tells Spring to automatically
// populate @CreatedDate field when a new booking is saved
@EntityListeners(AuditingEntityListener.class)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tour_id",referencedColumnName = "id",nullable = false)
    private Tour tour;

    @NotNull(message = "Number of Tickets are required")
    @Positive(message = "Number of Tickets must be greater than 0")
    private Integer numberOfTickets;

    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be greater than 0")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @NotNull(message = "Booking date is required")
    @Column(nullable = false)
    private LocalDate bookingDate;

    // Stripe gives this when checkout session is created
    // Frontend redirects user here to complete payment
    @Column(columnDefinition = "TEXT")
    private String stripeSessionUrl;
    @Column(length = 500)
    private  String paymentTransactionId;
    // @CreatedDate automatically sets this to current timestamp
    // when a new booking is saved to DB
    // The @Scheduled job uses this to find bookings older than 30 minutes
    // that are still PENDING (user never paid)
    @CreatedDate
    @Column(nullable = true, updatable = false)
    private LocalDateTime createdAt;

}
