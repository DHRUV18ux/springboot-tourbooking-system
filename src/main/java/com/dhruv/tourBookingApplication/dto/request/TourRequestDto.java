package com.dhruv.tourBookingApplication.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourRequestDto {
    @NotBlank(message = "Tour name is required")
    private String tourName;
    @NotBlank(message = "Tour description is required")
    @Size(min=10,max=500,message = "Tour description contains at least 10 characters and at most 500 characters ")
    private String tourDescription;
    @NotBlank(message = "Tour guide is required")
    private String tourGuide;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message ="End date is required" )
    private LocalDate endDate;
    @NotBlank(message = "Country is required")
    private String country;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater then 0 ")
    private Double price;

    @NotNull(message = "Tickets available is required")
    @Min(value = 1, message = "Tickets cannot be negative")
    private Integer ticketAvailable;

    @NotEmpty(message = "At least one meal is required")
    private List<String>meals=new ArrayList<>();
    @NotEmpty(message = "At least one activity is required")
    private List<String>activities=new ArrayList<>();

    private List<String>tourImages=new ArrayList<>();
    @NotNull(message = "Location id is required")
    @Positive(message = "Location id should be greater than 0")
    private Long locationId;
    @NotNull(message = "Lodging id is required")
    @Positive(message = "Lodging id should be greater than 0")
    private Long lodgingId;
    @NotNull(message = "Transport id is required")
    @Positive(message = "Transport id should be greater than 0")
    private Long transportId;
}
