package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.LodgingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LodgingResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.LodgingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/lodgings")
public class LodgingRestController {
    private final LodgingService lodgingService;
    @Autowired
    public LodgingRestController(LodgingService lodgingService){
        this.lodgingService=lodgingService;
    }
    @PostMapping
    public ResponseEntity<LodgingResponseDto> addLodging(@Valid @RequestBody LodgingRequestDto requestDto){
         LodgingResponseDto responseDto=lodgingService.addLodging(requestDto);
         return new ResponseEntity<LodgingResponseDto>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List> getAllLodging(){
        List<LodgingResponseDto>responseDtoList=lodgingService.getAllLodging();
        return new ResponseEntity<List>(responseDtoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LodgingResponseDto> getLodgingById(@PathVariable Long  id){
        LodgingResponseDto responseDto=lodgingService.getLodgingById(id);
        return new ResponseEntity<LodgingResponseDto>(responseDto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LodgingResponseDto> updateLodging(@PathVariable Long id,@Valid @RequestBody LodgingRequestDto requestDto){
        LodgingResponseDto responseDto=lodgingService.updateLodging(id,requestDto);
        return new ResponseEntity<LodgingResponseDto>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLodging(@PathVariable Long id){
        lodgingService.deleteLodging(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

