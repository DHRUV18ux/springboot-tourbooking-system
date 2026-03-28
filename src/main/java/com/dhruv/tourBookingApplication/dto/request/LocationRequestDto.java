package com.dhruv.tourBookingApplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationRequestDto {

    @NotBlank(message = "From location is required")
  private String fromLocation;
    @NotBlank(message = "To location is required")
  private String toLocation;
    @NotBlank(message = "Country is required")
  private String country;

    @NotNull(message = "distance is required")
    @Positive(message = "Distance must be greater then 0")
  private Double distance;

    @NotBlank(message = "Location description is required")
    @Size(
            min = 10,
            max = 500,
            message = "Description must be between 10 and 500 characters"
    )
  private String locationDescription;
    @Positive(message = "Travel time must be positive")
  private Integer estimatedTravelTime;
}
