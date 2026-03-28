package com.dhruv.tourBookingApplication.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {

    @NotBlank(message = "Name is required")
    @Size(min=2,max=20,message = "Name must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message ="Password is required")
    @Size(min=5, message = "Password must contain the at least 5 characters ")
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Contact number should be valid 10 digit number"
    )
    private String contactNo;

}
