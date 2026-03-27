package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {

}
