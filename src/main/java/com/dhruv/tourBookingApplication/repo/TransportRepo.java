package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepo extends JpaRepository<Transport,Long> {

}
