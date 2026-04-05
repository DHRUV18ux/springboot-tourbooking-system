package com.dhruv.tourBookingApplication.service.interfaces;

import com.dhruv.tourBookingApplication.dto.request.UserRegisterDto;
import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRegisterDto userRegisterDto);
    UserResponseDto getUserByEmail(String email);
    List<UserResponseDto> getAllUser();
}
