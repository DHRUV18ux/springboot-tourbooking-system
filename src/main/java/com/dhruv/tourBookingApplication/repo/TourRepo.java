package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepo extends JpaRepository<Tour,Long> {
      List<Tour>findTourByCountry(String country);
      List<Tour>findTourByPriceBetween(Double minPrice,Double maxPrice);
}
