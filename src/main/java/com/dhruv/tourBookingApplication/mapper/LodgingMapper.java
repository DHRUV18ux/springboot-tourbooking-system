package com.dhruv.tourBookingApplication.mapper;

import com.dhruv.tourBookingApplication.dto.request.LodgingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LodgingResponseDto;
import com.dhruv.tourBookingApplication.entites.Lodging;
import org.springframework.stereotype.Component;

@Component
public class LodgingMapper {

    public Lodging convertTolodging(LodgingRequestDto lodgingRequestDto){
        Lodging lodging=Lodging.builder()
                .lodgingName(lodgingRequestDto.getLodgingName())
                .lodgingType(lodgingRequestDto.getLodgingType())
                .lodgingDescription(lodgingRequestDto.getLodgingDescription())
                .address(lodgingRequestDto.getAddress())
                .rating(lodgingRequestDto.getRating())
                .build();
        return lodging;
    }
    public LodgingResponseDto convertToResponseDto(Lodging lodging){
        LodgingResponseDto lodgingResponseDto=LodgingResponseDto.builder()
                .id(lodging.getId())
                .lodgingName(lodging.getLodgingName())
                .lodgingDescription(lodging.getLodgingDescription())
                .lodgingType(lodging.getLodgingType())
                .address(lodging.getAddress())
                .rating(lodging.getRating())
                .build();
        return lodgingResponseDto;
    }
}
