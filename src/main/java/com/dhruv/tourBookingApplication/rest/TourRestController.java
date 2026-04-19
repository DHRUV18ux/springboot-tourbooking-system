package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TourDetailResponseDto;
import com.dhruv.tourBookingApplication.dto.response.TourSummaryResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.TourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourRestController {

    private final TourService tourService;

     @Autowired
    public TourRestController(TourService tourService){
        this.tourService=tourService;
    }


    @GetMapping
    public ResponseEntity<List<TourSummaryResponseDto>> getAllTours(){
         List<TourSummaryResponseDto>responseDtoList=tourService.getAllTours();
         return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDetailResponseDto>getTourById(@PathVariable Long id){
         TourDetailResponseDto responseDto=tourService.getTourById(id);
         return new ResponseEntity<TourDetailResponseDto>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TourSummaryResponseDto>> getToursByCountry(@RequestParam String country){
         List<TourSummaryResponseDto> responseDtoList=tourService.getToursByCountry(country);
         return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

     @GetMapping("/search/price")
    public ResponseEntity<List<TourSummaryResponseDto>> getTourByPriceRange(@RequestParam Double minPrice,@RequestParam Double maxPrice){
         List<TourSummaryResponseDto>responseDtoList=tourService.getToursByPriceRange(minPrice,maxPrice);
         return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    }

}
