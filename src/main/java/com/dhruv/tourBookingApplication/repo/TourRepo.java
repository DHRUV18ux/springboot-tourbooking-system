package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepo extends JpaRepository<Tour,Long> {

}
