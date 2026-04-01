package com.dhruv.tourBookingApplication.entites;

import com.dhruv.tourBookingApplication.dto.response.TransportResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name="transports")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Transport name is required")
    @Column(nullable = false)
    private String transportName;

    @NotBlank(message = "Transport type is required")
    @Column(nullable = false)
    private String transportType;

    @Positive(message = "Travel time must be positive")
    @Column(nullable = false)
    private Integer estimatedTravelTime;

    @NotBlank(message = "Transport Description is required")
    @Column(length = 1000)
    private String transportDescription;
}
