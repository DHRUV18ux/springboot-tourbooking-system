package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.LoginDto;
import com.dhruv.tourBookingApplication.dto.request.UserRegisterDto;
import com.dhruv.tourBookingApplication.dto.response.JwtResponseDto;
import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.entites.User;
import com.dhruv.tourBookingApplication.repo.UserRepo;
import com.dhruv.tourBookingApplication.service.impl.JwtTokenService;
import com.dhruv.tourBookingApplication.service.impl.MyUserDetailsService;
import com.dhruv.tourBookingApplication.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

     private final UserService userService;
     private final PasswordEncoder passwordEncoder;
     private final AuthenticationManager authenticationManager;
     private final JwtTokenService jwtTokenService;
     private final UserRepo userRepo;
     private final MyUserDetailsService userDetailsService;

      @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, UserRepo userRepo, MyUserDetailsService userDetailsService) {
        this.userService = userService;
          this.passwordEncoder = passwordEncoder;
          this.authenticationManager = authenticationManager;
          this.jwtTokenService = jwtTokenService;
          this.userRepo = userRepo;
          this.userDetailsService = userDetailsService;
      }

       @PostMapping("/register")
     public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
                String password=userRegisterDto.getPassword();
                String hashPass=passwordEncoder.encode(password);
                userRegisterDto.setPassword(hashPass);
          UserResponseDto userResponseDto=userService.registerUser(userRegisterDto);
          return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.CREATED);
     }

     @PostMapping("/login")
    public ResponseEntity<JwtResponseDto>loginUsers(@Valid @RequestBody LoginDto loginDto){
         Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
             User user=userRepo.findByEmail(loginDto.getEmail()).orElseThrow();
         UserDetails userDetails=userDetailsService.loadUserByUsername(user.getEmail());
             String token=jwtTokenService.generateToken(userDetails,user.getRole());
             JwtResponseDto response=JwtResponseDto.builder()
                     .token(token)
                     .email(user.getEmail())
                     .name(user.getName())
                     .role(user.getRole())
                     .build();
             return new ResponseEntity<JwtResponseDto>(response,HttpStatus.OK);
         }

}
