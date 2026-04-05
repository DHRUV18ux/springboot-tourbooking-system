package com.dhruv.tourBookingApplication.entites;

import com.dhruv.tourBookingApplication.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This is required")
    @Size(min = 2, max = 20, message = "minimum 2 and maximum 20 characters are allowed")
    private String name;

    @NotBlank(message = "This is required")
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9+.-]+.[a-zA-Z]{2,}$", message = "Invalid email")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "This is required")
    @Size(min = 5, message = "Password must be  at least 5 characters long! ")
    private String password;

    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be a valid 10 digit number!!")
    @Column(unique = true)
    private String contactNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;//ROLE_ADMIN OR ROLE_CUSTOMER

    private Boolean enabled = true;

}
