package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.LocationRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LocationResponseDto;
import com.dhruv.tourBookingApplication.entites.Location;
import com.dhruv.tourBookingApplication.exception.LocationNotFoundException;
import com.dhruv.tourBookingApplication.repo.LocationRepo;
import com.dhruv.tourBookingApplication.service.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepo locationRepo;

    @Autowired
    public LocationServiceImpl(LocationRepo locationRepo){
        this.locationRepo=locationRepo;
    }

    //private helper method to convert Location into LocationResponseDto
    private LocationResponseDto convertToResponseDto(Location location){
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

    //private Helper method which convert LocationRequestDto into the Location entity
    private Location convertToLocation(LocationRequestDto locationRequestDto){
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


    @Override
    public LocationResponseDto addLocation(LocationRequestDto locationRequestDto) {
       Location location=convertToLocation(locationRequestDto);
        Location savedLocation=locationRepo.save(location);
        return convertToResponseDto(savedLocation);
    }

    @Override
    public List<LocationResponseDto> getAllLocations() {
        List<Location> allLocation=locationRepo.findAll();
        List<LocationResponseDto>allLocationResponseDto=new ArrayList<>();
        for(Location location:allLocation){
            allLocationResponseDto.add(convertToResponseDto(location));
        }
        return allLocationResponseDto;
    }

    @Override
    public LocationResponseDto getLocationById(Long id) {
        Optional<Location>optional=locationRepo.findById(id);
        if(optional.isPresent()){
            Location location=optional.get();
            return convertToResponseDto(location);
        }
        else{
            throw new LocationNotFoundException("There is no location with this location id : "+id);
        }
    }

    @Override
    public LocationResponseDto updateLocation(long id, LocationRequestDto locationRequestDto) {
        Optional<Location>optional=locationRepo.findById(id);
        if(optional.isPresent()){
            Location location=optional.get();
           location.setFromLocation(locationRequestDto.getFromLocation());
           location.setToLocation(locationRequestDto.getToLocation());
           location.setCountry(locationRequestDto.getCountry());
           location.setDistance(locationRequestDto.getDistance());
           location.setLocationDescription(locationRequestDto.getLocationDescription());
           location.setEstimatedTravelTime(locationRequestDto.getEstimatedTravelTime());
           Location savedLocation=locationRepo.save(location);
           return convertToResponseDto(savedLocation);
        }
        else{
            throw new LocationNotFoundException("There is no location with this location id : "+id);
        }
    }

    @Override
    public void deleteLocation(Long id) {
        Optional<Location>optional=locationRepo.findById(id);
        if(optional.isPresent()){
            Location location=optional.get();
            locationRepo.delete(location);
        }
        else{
            throw new LocationNotFoundException("There is no Location with this Location id : "+id);
        }
    }
}
