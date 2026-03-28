package com.dhruv.tourBookingApplication.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportResponseDto {
    private Long id;
    private String transportName;
    private String transportType;
    private Integer estimatedTravelTime;
    private String transportDescription;
}
