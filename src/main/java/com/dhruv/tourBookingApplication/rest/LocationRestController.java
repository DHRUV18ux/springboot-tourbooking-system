package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.LocationRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LocationResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/locations")
public class LocationRestController {

    private  final LocationService locationService;

     @Autowired
    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }
    @PostMapping
    public ResponseEntity<LocationResponseDto> addLocation( @Valid  @RequestBody LocationRequestDto locationRequestDto){
         LocationResponseDto locationResponseDto=locationService.addLocation(locationRequestDto);
         return new ResponseEntity<LocationResponseDto>(locationResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List> getAllLocations(){
         List<LocationResponseDto> locations=locationService.getAllLocations();
         return new ResponseEntity<List>(locations,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponseDto> getLocationById(@PathVariable Long id){
         LocationResponseDto response=locationService.getLocationById(id);
         return new ResponseEntity<LocationResponseDto>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LocationResponseDto> updateLocationController(@PathVariable Long id, @Valid @RequestBody LocationRequestDto requestDto ){
         LocationResponseDto responseDto=locationService.updateLocation(id,requestDto);
         return new ResponseEntity<LocationResponseDto>(responseDto,HttpStatus.OK);
    }
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteLocation(@PathVariable Long id){
         locationService.deleteLocation(id);
         return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
     }
}
