package com.dhruv.tourBookingApplication.mapper;

import com.dhruv.tourBookingApplication.dto.response.*;
import com.dhruv.tourBookingApplication.entites.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourMapper {

      private final LocationMapper locationMapper;
      private final TransportMapper transportMapper;
      private final LodgingMapper lodgingMapper;

       @Autowired
      public TourMapper(LocationMapper locationMapper,TransportMapper transportMapper,LodgingMapper lodgingMapper){
          this.locationMapper=locationMapper;
          this.transportMapper=transportMapper;
          this.lodgingMapper=lodgingMapper;
      }


    public TourDetailResponseDto convertToTourDetailResponseDto(Tour tour){
        LocationResponseDto locationResponseDto=locationMapper.convertToResponseDto(tour.getLocation());
        LodgingResponseDto lodgingResponseDto=lodgingMapper.convertToResponseDto(tour.getLodging());
        TransportResponseDto transportResponseDto=transportMapper.convertToResponseDto(tour.getTransport());
        TourDetailResponseDto tourDetailResponseDto=TourDetailResponseDto.builder()
                .id(tour.getId())
                .tourName(tour.getTourName())
                .tourDescription(tour.getTourDescription())
                .tourGuide(tour.getTourGuide())
                .country(tour.getCountry())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .price(tour.getPrice())
                .ticketAvailable(tour.getTicketAvailable())
                .ticketsSold(tour.getTicketsSold())
                .meals(tour.getMeals())
                .activities(tour.getActivities())
                .tourImages(tour.getTourImages())
                .lodging(lodgingResponseDto)
                .location(locationResponseDto)
                .transport(transportResponseDto)
                .build();
           return tourDetailResponseDto;
    }
    public TourSummaryResponseDto convertToTourSummaryResponseDto(Tour tour) {
        TourSummaryResponseDto tourSummaryResponseDto = TourSummaryResponseDto.builder()
                .id(tour.getId())
                .tourName(tour.getTourName())
                .tourDescription(tour.getTourDescription())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .country(tour.getCountry())
                .ticketAvailable(tour.getTicketAvailable())
                .price(tour.getPrice())
                .thumbnailImage(
                        tour.getTourImages().isEmpty()
                                ? null
                                : tour.getTourImages().get(0)
                )
                .build();
        return tourSummaryResponseDto;
    }

}
