package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

     @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email){
         UserResponseDto userResponseDto=userService.getUserByEmail(email);
         return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
         List<UserResponseDto>responseDtoList=userService.getAllUser();
         return new ResponseEntity<List<UserResponseDto>>(responseDtoList,HttpStatus.OK);
    }

}
