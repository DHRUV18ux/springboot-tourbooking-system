package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.TransportRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TransportResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.TransportService;
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
@RequestMapping("/api/admin/transports")
@Tag(
        name = "Admin - Transport Management",
        description = "APIs for admin to add, update, delete and view all transports"
)
public class TransportRestController {

    private  final TransportService transportService;

    @Autowired
    public TransportRestController(TransportService transportService){
        this.transportService=transportService;
    }

      @PostMapping
      @Operation(
              summary = "Add a new transport",
              description = "Admin can add a new transport by providing transport details"
      )
      @ApiResponses(value = {
              @ApiResponse(responseCode = "201", description = "Transport created successfully"),
              @ApiResponse(responseCode = "400", description = "Invalid input data"),
              @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
              @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
              @ApiResponse(responseCode = "500", description = "Internal server error")
      })
     public ResponseEntity<TransportResponseDto> addTransport( @Valid @RequestBody TransportRequestDto transportRequestDto){
         TransportResponseDto transportResponseDto=transportService.addTransport(transportRequestDto);
         return new ResponseEntity<TransportResponseDto>(transportResponseDto, HttpStatus.CREATED);
     }

     @GetMapping
     @Operation(
             summary = "Get all transports",
             description = "Returns a list of all transports available in the system"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Transports retrieved successfully"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
     public ResponseEntity<List>getAllTransport(){
        List<TransportResponseDto>transportResponseDtolist=transportService.getAllTransports();
        return new ResponseEntity<List>(transportResponseDtolist,HttpStatus.OK);
     }

     @GetMapping("/{id}")
     @Operation(
             summary = "Get transport by ID",
             description = "Returns details of a specific transport by its ID"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Transport retrieved successfully"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
             @ApiResponse(responseCode = "404", description = "Transport not found with given ID"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
       public ResponseEntity<TransportResponseDto> getTransportById(@PathVariable Long id){
        TransportResponseDto transportResponseDto=transportService.getTransportById(id);
        return new ResponseEntity<TransportResponseDto>(transportResponseDto,HttpStatus.OK);
     }

     @PutMapping("/{id}")
     @Operation(
             summary = "Update a transport",
             description = "Admin can update the details of an existing transport by its ID"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Transport updated successfully"),
             @ApiResponse(responseCode = "400", description = "Invalid input data"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
             @ApiResponse(responseCode = "404", description = "Transport not found with given ID"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
    public ResponseEntity<TransportResponseDto> updateTransport(@PathVariable Long id,@Valid @RequestBody TransportRequestDto transportRequestDto){
         TransportResponseDto transportResponseDto=transportService.updateTransport(id,transportRequestDto);
          return new ResponseEntity<TransportResponseDto>(transportResponseDto,HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     @Operation(
             summary = "Delete a transport",
             description = "Admin can delete an existing transport by its ID"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "204", description = "Transport deleted successfully"),
             @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
             @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
             @ApiResponse(responseCode = "404", description = "Transport not found with given ID"),
             @ApiResponse(responseCode = "500", description = "Internal server error")
     })
    public ResponseEntity<Void>deleteTransport(@PathVariable Long id){
        transportService.deleteTransport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

}
