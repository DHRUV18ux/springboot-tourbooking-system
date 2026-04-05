package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.UserRegisterDto;
import com.dhruv.tourBookingApplication.dto.response.UserResponseDto;
import com.dhruv.tourBookingApplication.entites.User;
import com.dhruv.tourBookingApplication.enums.Role;
import com.dhruv.tourBookingApplication.exception.UserAlreadyExistsException;
import com.dhruv.tourBookingApplication.exception.UserNotFoundException;
import com.dhruv.tourBookingApplication.mapper.UserMapper;
import com.dhruv.tourBookingApplication.repo.UserRepo;
import com.dhruv.tourBookingApplication.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

      private final UserRepo userRepo;
      private final UserMapper userMapper;

       @Autowired
     public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
           this.userRepo = userRepo;
           this.userMapper = userMapper;
       }

     @Override
    public UserResponseDto registerUser(UserRegisterDto userRegisterDto) {
         Optional<User> isUser=userRepo.findByEmail(userRegisterDto.getEmail());
         if(isUser.isPresent()){
             throw new UserAlreadyExistsException("A user with this email already exists ");
         }

          Optional<User>userWithContact=userRepo.findByContactNo(userRegisterDto.getContactNo());
         if(userWithContact.isPresent()){
             throw new UserAlreadyExistsException("A user with this contact number already exists");
         }

           User user=User.builder()
                   .name(userRegisterDto.getName())
                   .email(userRegisterDto.getEmail())
                   .password(userRegisterDto.getPassword())
                   .contactNo(userRegisterDto.getContactNo())
                   .enabled(true)
                   .role(Role.ROLE_CUSTOMER)
                   .build();

          User savedUser=userRepo.save(user);
          return  userMapper.convertToResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        Optional<User> isUser=userRepo.findByEmail(email);
        User user=null;
        if(isUser.isPresent()){
            user=isUser.get();
        }
        else{
            throw new UserNotFoundException("user does not exists with email : "+email);
        }
        return userMapper.convertToResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User>users=userRepo.findAll();
        List<UserResponseDto>responseDtoList=new ArrayList<>();
        for(User user:users){
            responseDtoList.add(userMapper.convertToResponseDto(user));
        }
        return responseDtoList;
    }
}
