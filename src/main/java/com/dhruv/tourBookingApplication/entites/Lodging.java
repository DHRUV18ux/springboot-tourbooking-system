package com.dhruv.tourBookingApplication.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="lodgings")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Lodging {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @NotBlank(message ="lodgingName is required")
     @Column(nullable = false)
    private String lodgingName;

     @NotBlank(message = "lodgingType is required")
     @Column(nullable = false)
    private String lodgingType;

     @Column(length =1000)
    private String lodgingDescription;

     @NotBlank(message = "address is required")
     @Column(nullable = false)
    private String address;

     @NotNull(message = "rating is required")
     @DecimalMin(value="0.0",message = "Rating cannot be negative")
     @DecimalMax(value ="5.0",message = "Rating cannot exceed 5")
    private Double rating;

}
