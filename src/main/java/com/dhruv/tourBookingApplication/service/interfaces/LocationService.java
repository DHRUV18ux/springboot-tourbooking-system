package com.dhruv.tourBookingApplication.service.interfaces;

import com.dhruv.tourBookingApplication.dto.request.LocationRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LocationResponseDto;


import java.util.List;

public interface LocationService {
      LocationResponseDto addLocation(LocationRequestDto locationRequestDto);
    List<LocationResponseDto>getAllLocations();
     LocationResponseDto getLocationById(Long id);
     LocationResponseDto updateLocation(long id,LocationRequestDto locationRequestDto);
      void deleteLocation(Long id);
}
