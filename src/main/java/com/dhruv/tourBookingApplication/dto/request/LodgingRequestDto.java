package com.dhruv.tourBookingApplication.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingRequestDto {
     @NotBlank(message = "Lodging name is required")
    private String lodgingName;
     @NotBlank(message = "Lodging type is required")
    private String lodgingType;
     @NotBlank(message = "Lodging description is required")
     @Size(min=10 , max=500,
             message = "Lodging description should  be minimum of 10 characters and maximum of 500 characters ")
    private String lodgingDescription;
     @NotBlank(message = "Lodging address is required")
    private String address;
     @NotNull(message = "rating is required")
     @DecimalMin(value="0.0",message = "rating must be at least 0.0")
     @DecimalMax(value ="5.0",message = "rating must be at most 5.0")
    private Double rating;
}
