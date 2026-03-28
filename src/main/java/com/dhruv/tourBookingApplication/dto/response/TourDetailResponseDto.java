package com.dhruv.tourBookingApplication.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDetailResponseDto {

    private Long id;
    private String tourName;
    private String tourDescription;
    private String tourGuide;
    private LocalDate startDate;
    private LocalDate endDate;
    private String country;
    private Double price;
    private Integer ticketAvailable;
    private Integer ticketsSold;

    private List<String> meals;
    private List<String> activities;
    private List<String> tourImages;

    private LocationResponseDto location;
    private LodgingResponseDto lodging;
    private TransportResponseDto transport;
}
