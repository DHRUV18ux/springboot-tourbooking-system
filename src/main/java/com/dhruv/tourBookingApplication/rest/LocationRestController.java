package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.LocationRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LocationResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.LocationService;
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
@RequestMapping("/api/admin/locations")
@Tag(
        name = "Admin - Location Management",
        description = "APIs for admin to add, update, delete and view all locations"
)
public class LocationRestController {

    private  final LocationService locationService;

     @Autowired
    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @Operation(
            summary = "Add a new location",
            description = "Admin can add a new location by providing location details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Location created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LocationResponseDto> addLocation( @Valid  @RequestBody LocationRequestDto locationRequestDto){
         LocationResponseDto locationResponseDto=locationService.addLocation(locationRequestDto);
         return new ResponseEntity<LocationResponseDto>(locationResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get all locations",
            description = "Returns a list of all locations available in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List> getAllLocations(){
         List<LocationResponseDto> locations=locationService.getAllLocations();
         return new ResponseEntity<List>(locations,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get location by ID",
            description = "Returns details of a specific location by its ID"
    )
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Location retrieved successfully"),
         @ApiResponse(responseCode = "401",description = "Unauthorized Jwt token missing or invalid"),
         @ApiResponse(responseCode = "403",description = "Access denied Admin only"),
          @ApiResponse(responseCode = "404",description = "Location not found with given Id"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LocationResponseDto> getLocationById(@PathVariable Long id){
         LocationResponseDto response=locationService.getLocationById(id);
         return new ResponseEntity<LocationResponseDto>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a location",
            description = "Admin can update the details of an existing location by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "404", description = "Location not found with given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LocationResponseDto> updateLocationController(@PathVariable Long id, @Valid @RequestBody LocationRequestDto requestDto ){
         LocationResponseDto responseDto=locationService.updateLocation(id,requestDto);
         return new ResponseEntity<LocationResponseDto>(responseDto,HttpStatus.OK);
    }
     @DeleteMapping("/{id}")
     @Operation(
             summary = "Delete a location",
             description = "Admin can delete an existing location by its ID"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "204", description = "Location deleted successfully"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
             @ApiResponse(responseCode = "404", description = "Location not found with given ID"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
     public ResponseEntity<Void> deleteLocation(@PathVariable Long id){
         locationService.deleteLocation(id);
         return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
     }
}
