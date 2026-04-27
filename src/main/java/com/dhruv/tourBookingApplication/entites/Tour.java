package com.dhruv.tourBookingApplication.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"location", "lodging", "transport","meals", "activities", "tourImages"})
@Builder
@Table(name="tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tour name is required")
    @Column(nullable = false)
    private String tourName;

    @Column(length = 1000)
    private String tourDescription;

    @NotBlank(message = "Tour guide is required")
    @Column(nullable = false)
    private String tourGuide;

     @NotNull(message = "Start date is required")
    private LocalDate startDate;

     @NotNull(message ="End date is required" )
    private LocalDate endDate;

     @NotBlank(message = "Country is required")
     @Column(nullable = false)
     private String country;


     @ElementCollection
    // Tells Hibernate that this is a collection of basic (non-entity) types like String, Integer, etc.
// Hibernate will store this in a separate table.
     @CollectionTable(
             name="tour_meals",
             joinColumns = @JoinColumn(name="tour_id",referencedColumnName = "id")
     )
     // Defines the table name (tour_meals) and specifies the foreign key (tour_id)
     // which links this table to the Tour entity.
     @Column(name="meal")
     // Specifies the column name in the collection table where each value (meal) is stored.
     @Builder.Default
    private List<String>meals=new ArrayList<>();

     @ElementCollection
     @CollectionTable(name="tour_activities",joinColumns = @JoinColumn(name="tour_id",
             referencedColumnName = "id" ))
     @Column(name="activity")
     @Builder.Default
    private List<String>activities=new ArrayList<>();

     @NotNull(message = "Price is required")
     @Positive(message = "Price must be greater then 0 ")
    private Double price;

     @Column(nullable = false)
     @NotNull(message = "Tickets available is required")
     @Min(value = 0, message = "Tickets cannot be negative")
    private Integer ticketAvailable;

    @Builder.Default
    @Column(nullable = false)
    private Integer ticketsSold = 0;

    @ElementCollection
    @CollectionTable(name="tour_images",joinColumns = @JoinColumn(name="tour_id",referencedColumnName = "id"))
    @Column(name="images")
    @Builder.Default
    private List<String>tourImages=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id",referencedColumnName = "id")
    // here location_id is the forgign key in the tour table
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lodging_id",referencedColumnName = "id")
    private Lodging lodging;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="transport_id",referencedColumnName = "id")
    private Transport transport;
    // @Version tells JPA to add a 'version' column to the tours table
    // Every time this row is updated, version number increases by 1
    // If two users try to update the same row at the same time,
    // the second one gets OptimisticLockingFailureException
    // This PREVENTS overselling tickets
    @Version
    @Column(name = "version")
    @Builder.Default
    private Long version=0L;

}
