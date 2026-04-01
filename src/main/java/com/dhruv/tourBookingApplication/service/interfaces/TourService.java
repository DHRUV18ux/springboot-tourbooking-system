package com.dhruv.tourBookingApplication.service.interfaces;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TourDetailResponseDto;
import com.dhruv.tourBookingApplication.dto.response.TourSummaryResponseDto;

import java.util.List;

public interface TourService {
    TourDetailResponseDto addTour(TourRequestDto tourRequestDto);
     List<TourSummaryResponseDto> getAllTours();
     TourDetailResponseDto getTourById(Long id);
     TourDetailResponseDto updateTour(Long id,TourRequestDto tourRequestDto);
      void deleteTour(Long id);
      List<TourSummaryResponseDto> getToursByCountry(String country);
      List<TourSummaryResponseDto> getToursByPriceRange(Double minPrice,Double maxPrice);

}
