package com.dhruv.tourBookingApplication.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponseDto {
    private Long id;
     private String fromLocation;
     private String toLocation;
      private String country;
    private Double distance;
    private String locationDescription;
    private Integer estimatedTravelTime;
}
