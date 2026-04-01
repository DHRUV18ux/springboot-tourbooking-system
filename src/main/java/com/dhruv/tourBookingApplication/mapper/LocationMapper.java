package com.dhruv.tourBookingApplication.mapper;

import com.dhruv.tourBookingApplication.dto.request.LocationRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LocationResponseDto;
import com.dhruv.tourBookingApplication.entites.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationResponseDto convertToResponseDto(Location location){
        LocationResponseDto locationResponseDto=LocationResponseDto.builder()
                .id(location.getId())
                .fromLocation(location.getFromLocation())
                .toLocation(location.getToLocation())
                .country(location.getCountry())
                .locationDescription(location.getLocationDescription())
                .distance(location.getDistance())
                .estimatedTravelTime(location.getEstimatedTravelTime())
                .build();
        return locationResponseDto;
    }

    public  Location convertToLocation(LocationRequestDto locationRequestDto){
        Location location=Location.builder()
                .fromLocation(locationRequestDto.getFromLocation())
                .toLocation(locationRequestDto.getToLocation())
                .country(locationRequestDto.getCountry())
                .distance(locationRequestDto.getDistance())
                .locationDescription(locationRequestDto.getLocationDescription())
                .estimatedTravelTime(locationRequestDto.getEstimatedTravelTime())
                .build();
        return location;
    }

}
