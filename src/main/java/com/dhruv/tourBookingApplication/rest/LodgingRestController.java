package com.dhruv.tourBookingApplication.rest;

import com.dhruv.tourBookingApplication.dto.request.LodgingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LodgingResponseDto;
import com.dhruv.tourBookingApplication.service.interfaces.LodgingService;
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
@RequestMapping("/api/admin/lodgings")
@Tag(
        name = "Admin - Lodging Management",
        description = "APIs for admin to add, update, delete and view all lodgings"
)
public class LodgingRestController {
    private final LodgingService lodgingService;
    @Autowired
    public LodgingRestController(LodgingService lodgingService){
        this.lodgingService=lodgingService;
    }

    @PostMapping
    @Operation(
            summary = "Add a new lodging",
            description = "Admin can add a new lodging by providing lodging details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lodging created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    public ResponseEntity<LodgingResponseDto> addLodging(@Valid @RequestBody LodgingRequestDto requestDto){
         LodgingResponseDto responseDto=lodgingService.addLodging(requestDto);
         return new ResponseEntity<LodgingResponseDto>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping
     @Operation(
            summary = "Get all lodgings",
            description = "Returns a list of all lodgings available in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lodgings retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List> getAllLodging(){
        List<LodgingResponseDto>responseDtoList=lodgingService.getAllLodging();
        return new ResponseEntity<List>(responseDtoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Get lodging by ID",
            description = "Returns details of a specific lodging by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lodging retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "404", description = "Lodging not found with given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LodgingResponseDto> getLodgingById(@PathVariable Long  id){
        LodgingResponseDto responseDto=lodgingService.getLodgingById(id);
        return new ResponseEntity<LodgingResponseDto>(responseDto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a lodging",
            description = "Admin can update the details of an existing lodging by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lodging updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "404", description = "Lodging not found with given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LodgingResponseDto> updateLodging(@PathVariable Long id,@Valid @RequestBody LodgingRequestDto requestDto){
        LodgingResponseDto responseDto=lodgingService.updateLodging(id,requestDto);
        return new ResponseEntity<LodgingResponseDto>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a lodging",
            description = "Admin can delete an existing lodging by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lodging deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Access denied - Admin only"),
            @ApiResponse(responseCode = "404", description = "Lodging not found with given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteLodging(@PathVariable Long id){
        lodgingService.deleteLodging(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

