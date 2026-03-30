package com.dhruv.tourBookingApplication.service.interfaces;

import com.dhruv.tourBookingApplication.dto.request.TransportRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TransportResponseDto;

import java.util.List;

public interface TransportService {
    TransportResponseDto addTransport(TransportRequestDto transportRequestDto);
    List<TransportResponseDto> getAllTransports();
    TransportResponseDto getTransportById(Long id);
    TransportResponseDto updateTransport(Long id,TransportRequestDto transportRequestDto);
    void deleteTransport(Long id);
}
