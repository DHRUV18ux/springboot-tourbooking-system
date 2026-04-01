package com.dhruv.tourBookingApplication.mapper;

import com.dhruv.tourBookingApplication.dto.request.TransportRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TransportResponseDto;
import com.dhruv.tourBookingApplication.entites.Transport;
import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Component;

@Component
public class TransportMapper {

    public Transport convertToTransport(TransportRequestDto transportRequestDto){
        Transport transport=Transport.builder()
                .transportName(transportRequestDto.getTransportName())
                .transportType(transportRequestDto.getTransportType())
                .transportDescription(transportRequestDto.getTransportDescription())
                .estimatedTravelTime(transportRequestDto.getEstimatedTravelTime())
                .build();
        return transport;
    }

    public TransportResponseDto convertToResponseDto(Transport transport) {
        TransportResponseDto responseDto=TransportResponseDto.builder()
                .id(transport.getId())
                .transportName(transport.getTransportName())
                .transportType(transport.getTransportType())
                .transportDescription(transport.getTransportDescription())
                .estimatedTravelTime(transport.getEstimatedTravelTime())
                .build();
        return responseDto;
    }

}
