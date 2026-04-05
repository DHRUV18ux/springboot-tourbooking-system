package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.UserRegisterDto;
import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

     private final UserService userService;

      @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

       @PostMapping("/register")
     public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
          UserResponseDto userResponseDto=userService.registerUser(userRegisterDto);
          return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.CREATED);
     }
}
