package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

}
