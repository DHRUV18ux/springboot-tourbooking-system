package com.dhruv.tourBookingApplication.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="transports")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transportName;
    private String transportType;
    private String estimatedTravelTime;
    private String transportDescription;
}
