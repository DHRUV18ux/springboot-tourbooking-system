package com.dhruv.tourBookingApplication.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tourName;
    private String tourDescription;
    private String tourGuide;

    private LocalDate startDate;
    private LocalDate endDate;

     @ElementCollection
    // Tells Hibernate that this is a collection of basic (non-entity) types like String, Integer, etc.
// Hibernate will store this in a separate table.
     @CollectionTable(
             name="tour_meals",
             joinColumns = @JoinColumn(name="tour_id")
     )
     // Defines the table name (tour_meals) and specifies the foreign key (tour_id)
     // which links this table to the Tour entity.
     @Column(name="meal")
     // Specifies the column name in the collection table where each value (meal) is stored.
    private List<String>meals=new ArrayList<>();

     @ElementCollection
     @CollectionTable(name="tour_activities",joinColumns = @JoinColumn(name="tour_id"))
     @Column(name="activity")
    private List<String>activities=new ArrayList<>();
    private Double price;
    private Integer ticketAvailable;
    @ElementCollection
    @CollectionTable(name="tour_images",joinColumns = @JoinColumn(name="tour_id"))
    @Column(name="images")
    private List<String>tourImages=new ArrayList<>();

    @OneToOne
    @JoinColumn(name="location_id",referencedColumnName = "id")
    // here location_id is the forgign key in the tour table
    private Location location;
    @OneToOne
    @JoinColumn(name="lodging_id",referencedColumnName = "id")
    private Lodging lodging;
    @OneToOne
    @JoinColumn(name="transport_id",referencedColumnName = "id")
    private Transport transport;

}
