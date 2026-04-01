package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.TransportRequestDto;
import com.dhruv.tourBookingApplication.dto.response.TransportResponseDto;
import com.dhruv.tourBookingApplication.entites.Transport;
import com.dhruv.tourBookingApplication.exception.TransportNotFoundException;
import com.dhruv.tourBookingApplication.mapper.TransportMapper;
import com.dhruv.tourBookingApplication.repo.TransportRepo;
import com.dhruv.tourBookingApplication.service.interfaces.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepo transportRepo;
    private final TransportMapper transportMapper;

     @Autowired
    public TransportServiceImpl(TransportRepo transportRepo,TransportMapper transportMapper){
         this.transportRepo=transportRepo;
         this.transportMapper=transportMapper;
    }

    @Override
    public TransportResponseDto addTransport(TransportRequestDto transportRequestDto) {
        Transport transport=transportMapper.convertToTransport(transportRequestDto);
        Transport savedTransport=transportRepo.save(transport);
        return   transportMapper.convertToResponseDto(savedTransport);
    }


    @Override
    public List<TransportResponseDto> getAllTransports() {
        List<Transport> allTransports=transportRepo.findAll();
        List<TransportResponseDto> transportResponseDtos=new ArrayList<>();
        for(Transport transport:allTransports){
            transportResponseDtos.add(transportMapper.convertToResponseDto(transport));
        }
        return transportResponseDtos;
    }

    @Override
    public TransportResponseDto getTransportById(Long id) {
        Optional<Transport> optional=transportRepo.findById(id);
        if(optional.isPresent()){
            Transport transport=optional.get();
            return transportMapper.convertToResponseDto(transport);
        }
        else{
            throw new TransportNotFoundException("Transport with this id "+ id + " does not exits");
        }
    }

    @Override
    public TransportResponseDto updateTransport(Long id,TransportRequestDto transportRequestDto) {
        Optional<Transport>optional=transportRepo.findById(id);
        if(optional.isPresent()){
            Transport transport=optional.get();
            transport.setTransportName(transportRequestDto.getTransportName());
            transport.setTransportType(transportRequestDto.getTransportType());
            transport.setTransportDescription(transportRequestDto.getTransportDescription());
            transport.setEstimatedTravelTime(transportRequestDto.getEstimatedTravelTime());
            Transport updatedTransport=transportRepo.save(transport);
            return  transportMapper.convertToResponseDto(updatedTransport);
        }
        else{
            throw new TransportNotFoundException("Transport with this id : "+ id +" does not exits");
        }
    }
    @Override
    public void deleteTransport(Long id) {
         Optional<Transport>optional=transportRepo.findById(id);
         if(optional.isPresent()){
             Transport transport=optional.get();
             transportRepo.delete(transport);
         }
         else {
             throw new TransportNotFoundException("Transport with this id : "+ id +" does not exits");
         }
    }
}
