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
public class TransportRequestDto {
    @NotBlank(message = "Transport name is required")
    private String transportName;
    @NotBlank(message = "Transport type is required")
    private String transportType;
    @NotNull(message = "Estimated travel time is required")
    @Positive(message = "Estimated travel time should be greater then 0")
    private Integer estimatedTravelTime;
    @NotBlank(message = "Transport Description is required")
    @Size(min=10,max=500,message = "Transport Description  must be  min of 10 characters and maximum of 500 characters")
    private String transportDescription;
}
