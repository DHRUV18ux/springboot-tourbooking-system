package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.LocationRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LocationResponseDto;
import com.dhruv.tourBookingApplication.entites.Location;
import com.dhruv.tourBookingApplication.exception.LocationNotFoundException;
import com.dhruv.tourBookingApplication.mapper.LocationMapper;
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
    private final LocationMapper locationMapper;

    @Autowired
    public LocationServiceImpl(LocationRepo locationRepo,LocationMapper locationMapper){
        this.locationRepo=locationRepo;
        this.locationMapper=locationMapper;
    }


    @Override
    public LocationResponseDto addLocation(LocationRequestDto locationRequestDto) {
       Location location= locationMapper.convertToLocation(locationRequestDto);
        Location savedLocation=locationRepo.save(location);
        return locationMapper.convertToResponseDto(savedLocation);
    }

    @Override
    public List<LocationResponseDto> getAllLocations() {
        List<Location> allLocation=locationRepo.findAll();
        List<LocationResponseDto>allLocationResponseDto=new ArrayList<>();
        for(Location location:allLocation){
            allLocationResponseDto.add(locationMapper.convertToResponseDto(location));
        }
        return allLocationResponseDto;
    }

    @Override
    public LocationResponseDto getLocationById(Long id) {
        Optional<Location>optional=locationRepo.findById(id);
        if(optional.isPresent()){
            Location location=optional.get();
            return  locationMapper.convertToResponseDto(location);
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
           return locationMapper.convertToResponseDto(savedLocation);
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
