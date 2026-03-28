package com.dhruv.tourBookingApplication.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="locations")
@Builder
public class Location {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @NotBlank(message = "from location is required")
     @Column(nullable = false)
    private String fromLocation;

     @NotBlank(message = "toLocation is required")
     @Column(nullable = false)
    private String toLocation;

     @NotBlank(message = "country is required")
     @Column(nullable = false)
    private String country;

     @NotNull(message = "distance is required")
     @Positive(message = "distance must be a positive number")
    private Double distance;

     @NotBlank(message = "Location description is required")
    @Column(length = 1000)
    private String locationDescription;

    @Positive(message = "Travel time must be positive")
    @Column(nullable = false)
    private Integer estimatedTravelTime;


}
