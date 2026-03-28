package com.dhruv.tourBookingApplication.dto.response;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourSummaryResponseDto {
    private Long id;
    private String tourName;
    private String tourDescription;
    private String country;
    private Double price;
    private Integer ticketAvailable;
    private LocalDate startDate;
    private LocalDate endDate;
    private String thumbnailImage;
}
