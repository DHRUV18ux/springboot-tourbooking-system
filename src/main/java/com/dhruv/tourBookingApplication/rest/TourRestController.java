package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TourDetailResponseDto;
import com.dhruv.tourBookingApplication.dto.response.TourSummaryResponseDto;
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

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@Tag(
        name = "Tour APIs",
        description = "Public APIs to view, search tours by country and filter by price range. No authentication required"
)
public class TourRestController {

    private final TourService tourService;

     @Autowired
    public TourRestController(TourService tourService){
        this.tourService=tourService;
    }


    @GetMapping
    @Operation(
            summary = "Get all tours",
            description = "Returns a summary list of all available tours. No authentication required"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tours retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TourSummaryResponseDto>> getAllTours(){
         List<TourSummaryResponseDto>responseDtoList=tourService.getAllTours();
         return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get tour by ID",
            description = "Returns full details of a specific tour including location, lodging and transport. No authentication required"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tour retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Tour not found with given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TourDetailResponseDto>getTourById(@PathVariable Long id){
         TourDetailResponseDto responseDto=tourService.getTourById(id);
         return new ResponseEntity<TourDetailResponseDto>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search tours by country",
            description = "Returns all tours available in the given country. No authentication required"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tours retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No tours found for given country"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TourSummaryResponseDto>> getToursByCountry(@RequestParam String country){
         List<TourSummaryResponseDto> responseDtoList=tourService.getToursByCountry(country);
         return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

     @GetMapping("/search/price")
     @Operation(
             summary = "Search tours by price range",
             description = "Returns all tours within the given minimum and maximum price range. No authentication required"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Tours retrieved successfully"),
             @ApiResponse(responseCode = "400", description = "Invalid price range - min price cannot be greater than max price"),
             @ApiResponse(responseCode = "404", description = "No tours found in given price range"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
    public ResponseEntity<List<TourSummaryResponseDto>> getTourByPriceRange(@RequestParam Double minPrice,@RequestParam Double maxPrice){
         List<TourSummaryResponseDto>responseDtoList=tourService.getToursByPriceRange(minPrice,maxPrice);
         return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

}
