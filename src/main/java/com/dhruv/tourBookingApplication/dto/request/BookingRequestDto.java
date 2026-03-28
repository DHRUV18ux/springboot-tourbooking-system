package com.dhruv.tourBookingApplication.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDto {

    @NotNull(message = "Tour id is required")
    @Positive(message = "Tour id must be greater than 0")
    private Long tourId;

    // Max 50 tickets per booking as per your requirement
    @NotNull(message = "Number of tickets is required")
    @Min(value = 1, message = "At least 1 ticket must be booked")
    @Max(value = 50, message = "Maximum 50 tickets can be booked at once")
    private Integer numberOfTickets;
}
