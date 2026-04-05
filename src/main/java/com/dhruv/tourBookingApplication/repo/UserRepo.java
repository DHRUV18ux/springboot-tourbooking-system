package com.dhruv.tourBookingApplication.repo;

import com.dhruv.tourBookingApplication.entites.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String email);
     Optional<User> findByContactNo(String contact);
}
