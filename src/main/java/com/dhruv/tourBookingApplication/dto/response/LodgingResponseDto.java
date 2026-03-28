package com.dhruv.tourBookingApplication.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingResponseDto {
    private Long id;
    private String lodgingName;
    private String lodgingType;
    private String lodgingDescription;
    private String address;
    private Double rating;
}
