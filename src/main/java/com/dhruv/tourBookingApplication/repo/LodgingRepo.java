package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LodgingRepo extends JpaRepository<Lodging,Long> {
}
