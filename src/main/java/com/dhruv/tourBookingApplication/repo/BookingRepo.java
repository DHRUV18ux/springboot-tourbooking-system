package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.Booking;
import com.dhruv.tourBookingApplication.entites.Tour;
import com.dhruv.tourBookingApplication.entites.User;
import com.dhruv.tourBookingApplication.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.dhruv.tourBookingApplication.enums.PaymentStatus.PENDING;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
     Optional<Booking> findByTour_IdAndCustomer_EmailAndPaymentStatus(Long tourId, String userEmail, PaymentStatus paymentStatus);
     List<Booking> findByCustomer(User user);
     List<Booking> findByTour(Tour tour);
     List<Booking> findByPaymentStatusAndCreatedAtBefore(PaymentStatus paymentStatus, LocalDateTime cutoffTime);
}
