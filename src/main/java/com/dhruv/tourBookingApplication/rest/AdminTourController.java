package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TourDetailResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tours")
@Tag(
        name="Admin Tour api",
        description = "This Api is for the Admin to add new Tours , update the existing tours" +
                "and delete a tour "
)
public class AdminTourController {

    private final TourService tourService;

     @Autowired
    public AdminTourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    @Operation(
            summary = "Add a new tour",
            description = "Admin can add a tour with all details like location,lodging,transport by passing TourRequestDto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tour created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TourDetailResponseDto> addTour(@Valid @RequestBody TourRequestDto tourRequestDto){
        TourDetailResponseDto responseDto=tourService.addTour(tourRequestDto);
        return new ResponseEntity<TourDetailResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing tour",
            description = "Admin can update the details of an existing Tour with its TourId"
    )
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Tour updated successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid input data "),
            @ApiResponse(responseCode ="403",description = "Access denied admin only"),
            @ApiResponse(responseCode = "404",description = "Tour not found with given tourId"),
            @ApiResponse(responseCode = "500",description = "Internal server error")
    })
 public ResponseEntity<TourDetailResponseDto>updateTour(@PathVariable Long id,@Valid @RequestBody TourRequestDto tourRequestDto){
        TourDetailResponseDto responseDto=tourService.updateTour(id,tourRequestDto);
        return new ResponseEntity<TourDetailResponseDto>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a tour",
            description = "Admin can delete an existing tour by its tourId"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tour deleted successfully"),
            @ApiResponse(responseCode = "403",description = "Access denied admin only"),
            @ApiResponse(responseCode = "404",description = "Tour not found with given tourID"),
            @ApiResponse(responseCode = "500",description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTour(@PathVariable Long id){
        tourService.deleteTour(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
