package com.dhruv.tourBookingApplication.dto.response;

import com.dhruv.tourBookingApplication.enums.Role;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private Role role;
}
