package com.dhruv.tourBookingApplication.service.interfaces;

import com.dhruv.tourBookingApplication.dto.request.LodgingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LodgingResponseDto;

import java.util.List;

public interface LodgingService {
     LodgingResponseDto addLodging(LodgingRequestDto lodgingRequestDto);
     List<LodgingResponseDto> getAllLodging();
     LodgingResponseDto getLodgingById(Long id);
     LodgingResponseDto updateLodging(Long id,LodgingRequestDto lodgingRequestDto);
     void deleteLodging(Long id);
}
