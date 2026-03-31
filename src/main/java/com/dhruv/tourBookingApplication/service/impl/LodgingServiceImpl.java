package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.LodgingRequestDto;
import com.dhruv.tourBookingApplication.dto.response.LodgingResponseDto;
import com.dhruv.tourBookingApplication.entites.Lodging;
import com.dhruv.tourBookingApplication.exception.LodgingNotFoundException;
import com.dhruv.tourBookingApplication.repo.LodgingRepo;
import com.dhruv.tourBookingApplication.service.interfaces.LodgingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LodgingServiceImpl implements LodgingService {
    private final LodgingRepo lodgingRepo;

    @Autowired
    public LodgingServiceImpl(LodgingRepo lodgingRepo){
        this.lodgingRepo=lodgingRepo;
    }

    private Lodging convertTolodging(LodgingRequestDto lodgingRequestDto){
               Lodging lodging=Lodging.builder()
                       .lodgingName(lodgingRequestDto.getLodgingName())
                       .lodgingType(lodgingRequestDto.getLodgingType())
                       .lodgingDescription(lodgingRequestDto.getLodgingDescription())
                       .address(lodgingRequestDto.getAddress())
                       .rating(lodgingRequestDto.getRating())
                       .build();
               return lodging;
    }
    private LodgingResponseDto convertToResponseDto(Lodging lodging){
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
        @Override
    public LodgingResponseDto addLodging(LodgingRequestDto lodgingRequestDto) {
      Lodging lodging=convertTolodging(lodgingRequestDto);
      Lodging savedLodging=lodgingRepo.save(lodging);
      return convertToResponseDto(savedLodging);
    }

    @Override
    public List<LodgingResponseDto> getAllLodging() {
        List<Lodging>allLodging=lodgingRepo.findAll();
        List<LodgingResponseDto>responseDtoList=new ArrayList<>();
        for(Lodging lodging:allLodging){
            responseDtoList.add(convertToResponseDto(lodging));
        }
        return responseDtoList;
    }

    @Override
    public LodgingResponseDto getLodgingById(Long id) {
        Optional<Lodging>optional=lodgingRepo.findById(id);
        if(optional.isPresent()){
            Lodging lodging=optional.get();
            return convertToResponseDto(lodging);
        }
        else{
            throw new LodgingNotFoundException("Lodging with this id : "+ id +" does not exists");
        }
    }

    @Override
    public LodgingResponseDto updateLodging(Long id, LodgingRequestDto lodgingRequestDto) {
        Optional<Lodging> optional = lodgingRepo.findById(id);
        if (optional.isPresent()) {
            Lodging lodging = optional.get();
            lodging.setLodgingName(lodgingRequestDto.getLodgingName());
            lodging.setLodgingType(lodgingRequestDto.getLodgingType());
            lodging.setLodgingDescription(lodgingRequestDto.getLodgingDescription());
            lodging.setAddress(lodgingRequestDto.getAddress());
            lodging.setRating(lodgingRequestDto.getRating());
            Lodging updatedLodging = lodgingRepo.save(lodging);
            return convertToResponseDto(updatedLodging);
        } else {
            throw new LodgingNotFoundException("Lodging with this id : " + id + " does not exists");
        }
    }
    @Override
    public void deleteLodging(Long id) {
         Optional<Lodging>optional=lodgingRepo.findById(id);
         if(optional.isPresent()){
             lodgingRepo.delete(optional.get());
         }
         else{
             throw new LodgingNotFoundException("Lodging with this id : " + id + " does not exists");
         }
    }

}
