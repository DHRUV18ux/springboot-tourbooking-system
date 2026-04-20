package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@Tag(
        name = "Admin - User Management",
        description = "APIs for admin to view all users and search a specific user by email"
)
public class UserController {

    private final UserService userService;

     @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    @Operation(
            summary = "Get user by email",
            description = "Admin can fetch details of a specific user by their email address"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "404", description = "User not found with given email"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email){
         UserResponseDto userResponseDto=userService.getUserByEmail(email);
         return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all users",
            description = "Returns a list of all registered users in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
         List<UserResponseDto>responseDtoList=userService.getAllUser();
         return new ResponseEntity<List<UserResponseDto>>(responseDtoList,HttpStatus.OK);
    }

}
