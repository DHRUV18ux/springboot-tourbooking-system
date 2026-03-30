package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.TransportRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TransportResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.TransportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/transports")
public class TransportRestController {

    private  final TransportService transportService;

    @Autowired
    public TransportRestController(TransportService transportService){
        this.transportService=transportService;
    }

      @PostMapping
     public ResponseEntity<TransportResponseDto> addTransport( @Valid @RequestBody TransportRequestDto transportRequestDto){
         TransportResponseDto transportResponseDto=transportService.addTransport(transportRequestDto);
         return new ResponseEntity<TransportResponseDto>(transportResponseDto, HttpStatus.CREATED);
     }

     @GetMapping
     public ResponseEntity<List>getAllTransport(){
        List<TransportResponseDto>transportResponseDtolist=transportService.getAllTransports();
        return new ResponseEntity<List>(transportResponseDtolist,HttpStatus.OK);
     }

     @GetMapping("/{id}")
       public ResponseEntity<TransportResponseDto> getTransportById(@PathVariable Long id){
        TransportResponseDto transportResponseDto=transportService.getTransportById(id);
        return new ResponseEntity<TransportResponseDto>(transportResponseDto,HttpStatus.OK);
     }

     @PutMapping("/{id}")
    public ResponseEntity<TransportResponseDto> updateTransport(@PathVariable Long id,@Valid @RequestBody TransportRequestDto transportRequestDto){
         TransportResponseDto transportResponseDto=transportService.updateTransport(id,transportRequestDto);
          return new ResponseEntity<TransportResponseDto>(transportResponseDto,HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteTransport(@PathVariable Long id){
        transportService.deleteTransport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

}
