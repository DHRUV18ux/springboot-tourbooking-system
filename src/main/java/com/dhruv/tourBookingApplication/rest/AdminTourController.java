package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TourDetailResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tours")
public class AdminTourController {

    private final TourService tourService;

     @Autowired
    public AdminTourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<TourDetailResponseDto> addTour(@Valid @RequestBody TourRequestDto tourRequestDto){
        TourDetailResponseDto responseDto=tourService.addTour(tourRequestDto);
        return new ResponseEntity<TourDetailResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourDetailResponseDto>updateTour(@PathVariable Long id,@Valid @RequestBody TourRequestDto tourRequestDto){
        TourDetailResponseDto responseDto=tourService.updateTour(id,tourRequestDto);
        return new ResponseEntity<TourDetailResponseDto>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id){
        tourService.deleteTour(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
