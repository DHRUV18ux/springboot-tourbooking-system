package com.dhruv.tourBookingApplication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
     @NotBlank(message = "Email is required")
     @Email(message = "Invalid email format")
    private String email;
     @NotBlank(message = "Password is required")
    private String password;
}
