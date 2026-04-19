package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.entites.User;
import com.dhruv.tourBookingApplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private  final UserRepo userRepo;

     @Autowired
    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>isUser=userRepo.findByEmail(username);
        if(isUser.isEmpty()){
            throw new UsernameNotFoundException("User with this email does not exists");
        }
        User user=isUser.get();
        return new UserPrincipal(user);
    }
}
