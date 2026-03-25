package com.dhruv.tourBookingApplication.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="lodgings")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Lodging {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(nullable = false)
    private String lodgingName;

    private String lodgingType;
    private String lodgingDescription;
    private String address;
    private Double rating;

}
