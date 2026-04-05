package com.dhruv.tourBookingApplication.mapper;

import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.entites.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto convertToResponseDto(User user){
        UserResponseDto userResponseDto=UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contactNo(user.getContactNo())
                .role(user.getRole())
                .build();
        return userResponseDto;
    }
}
