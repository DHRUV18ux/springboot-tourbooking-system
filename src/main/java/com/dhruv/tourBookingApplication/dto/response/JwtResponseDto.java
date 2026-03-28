package com.dhruv.tourBookingApplication.dto.response;

import com.dhruv.tourBookingApplication.enums.Role;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponseDto {
    private String token;
    private Role role;
    private String name;
    private  String email;
}
