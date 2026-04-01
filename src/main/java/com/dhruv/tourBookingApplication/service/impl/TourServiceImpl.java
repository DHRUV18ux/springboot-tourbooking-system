package com.dhruv.tourBookingApplication.service.impl;

import com.dhruv.tourBookingApplication.dto.request.TourRequestDto;
import com.dhruv.tourBookingApplication.dto.response.*;
import com.dhruv.tourBookingApplication.entites.Location;
import com.dhruv.tourBookingApplication.entites.Lodging;
import com.dhruv.tourBookingApplication.entites.Tour;
import com.dhruv.tourBookingApplication.entites.Transport;
import com.dhruv.tourBookingApplication.exception.*;
import com.dhruv.tourBookingApplication.mapper.TourMapper;
import com.dhruv.tourBookingApplication.repo.LocationRepo;
import com.dhruv.tourBookingApplication.repo.LodgingRepo;
import com.dhruv.tourBookingApplication.repo.TourRepo;
import com.dhruv.tourBookingApplication.repo.TransportRepo;
import com.dhruv.tourBookingApplication.service.interfaces.LocationService;
import com.dhruv.tourBookingApplication.service.interfaces.LodgingService;
import com.dhruv.tourBookingApplication.service.interfaces.TourService;
import com.dhruv.tourBookingApplication.service.interfaces.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepo tourRepo;
    private final LocationRepo  locationRepo;
    private final LodgingRepo lodgingRepo;
    private final TransportRepo transportRepo;
    private final TourMapper tourMapper;

    @Autowired
    public TourServiceImpl(TourRepo tourRepo,LocationRepo locationRepo,LodgingRepo lodgingRepo,TransportRepo transportRepo,
                         TourMapper tourMapper){
        this.tourRepo=tourRepo;
        this.locationRepo=locationRepo;
        this.lodgingRepo=lodgingRepo;
        this.transportRepo=transportRepo;
        this.tourMapper=tourMapper;
    }


    @Override
    public TourDetailResponseDto addTour(TourRequestDto tourRequestDto) {

        if (tourRequestDto.getEndDate().isBefore(tourRequestDto.getStartDate())) {
            throw new InvalidTourDateException(
                    "End date cannot be before start date"
            );
        }

        Optional<Location>isLocation=locationRepo.findById(tourRequestDto.getLocationId());
           Location location=null;
            if(isLocation.isPresent()){
                location=isLocation.get();
            }
            else{
                throw new LocationNotFoundException("There is no location exits with entered locationId");
            }
            Optional<Lodging>isLodging=lodgingRepo.findById(tourRequestDto.getLodgingId());
            Lodging lodging=null;
            if(isLodging.isPresent()){
                lodging=isLodging.get();
            }
            else{
                throw new LodgingNotFoundException("There is no lodging exits with entered lodgingId ");
            }
            Optional<Transport> isTransport=transportRepo.findById(tourRequestDto.getTransportId());
            Transport transport=null;
            if(isTransport.isPresent()){
                transport=isTransport.get();
            }
            else{
                throw new TransportNotFoundException("There is no Transport exists with entered transportId");
            }

          Tour tour=Tour.builder()
                  .tourName(tourRequestDto.getTourName())
                  .tourGuide(tourRequestDto.getTourGuide())
                  .tourDescription(tourRequestDto.getTourDescription())
                  .startDate(tourRequestDto.getStartDate())
                  .endDate(tourRequestDto.getEndDate())
                  .country(tourRequestDto.getCountry())
                  .price(tourRequestDto.getPrice())
                  .ticketAvailable(tourRequestDto.getTicketAvailable())
                  .meals(tourRequestDto.getMeals())
                  .activities(tourRequestDto.getActivities())
                  .tourImages(tourRequestDto.getTourImages())
                  .location(location)
                  .lodging(lodging)
                  .transport(transport)
                  .build();
        Tour savedTour=tourRepo.save(tour);
        return tourMapper.convertToTourDetailResponseDto(savedTour);
    }

    @Override
    public List<TourSummaryResponseDto> getAllTours() {
        List<Tour> tours=tourRepo.findAll();
        List<TourSummaryResponseDto>responseDtoList=new ArrayList<>();
        for(Tour tour:tours){
            responseDtoList.add(tourMapper.convertToTourSummaryResponseDto(tour));
        }
        return responseDtoList;
    }

    @Override
    public TourDetailResponseDto getTourById(Long id) {
        Optional<Tour>optional=tourRepo.findById(id);
        if(optional.isPresent()){
            Tour tour=optional.get();
            TourDetailResponseDto tourDetailResponseDto=tourMapper.convertToTourDetailResponseDto(tour);
            return tourDetailResponseDto;
        }
        else{
            throw new TourNotFoundException("Tour with the entered id : "+ id +" does not present");
        }
    }

    @Override
    public TourDetailResponseDto updateTour(Long id, TourRequestDto tourRequestDto) {
         Optional<Tour>optional=tourRepo.findById(id);
         if(optional.isPresent()){
               if(tourRequestDto.getEndDate().isBefore(tourRequestDto.getStartDate())){
                   throw new InvalidTourDateException( "End date cannot be before start date");
               }
             Long locationId=tourRequestDto.getLocationId();
              Optional<Location>isLocation=locationRepo.findById(locationId);
              Location location=null;
              if(isLocation.isPresent()){
                  location=isLocation.get();
              }
              else{
                  throw new LocationNotFoundException("There is no  location exists with this location id : "+locationId);
              }
             Long lodgingId=tourRequestDto.getLodgingId();
             Optional<Lodging>isLodging =lodgingRepo.findById(lodgingId);
             Lodging lodging=null;
             if(isLodging.isPresent()){
                 lodging=isLodging.get();
             }
             else{
                 throw new LodgingNotFoundException("There is no lodging exists with this lodging id : "+lodgingId);
             }
             Long transportId=tourRequestDto.getTransportId();
             Optional<Transport>isTransport=transportRepo.findById(transportId);
             Transport transport=null;
              if(isTransport.isPresent()){
                  transport=isTransport.get();
              }
              else{
                  throw new TransportNotFoundException("There is not transport exists ");
              }

             Tour tour=optional.get();
             tour.setTourName(tourRequestDto.getTourName());
             tour.setTourGuide(tourRequestDto.getTourGuide());
             tour.setTourDescription(tourRequestDto.getTourDescription());
             tour.setCountry(tourRequestDto.getCountry());
             tour.setActivities(tourRequestDto.getActivities());
             tour.setMeals(tourRequestDto.getMeals());
             tour.setTourImages(tourRequestDto.getTourImages());
             tour.setStartDate(tourRequestDto.getStartDate());
             tour.setEndDate(tourRequestDto.getEndDate());
             tour.setTicketAvailable(tourRequestDto.getTicketAvailable());
             tour.setPrice(tourRequestDto.getPrice());
             tour.setLocation(location);
             tour.setLodging(lodging);
             tour.setTransport(transport);
             Tour updatedTour=tourRepo.save(tour);
             return tourMapper.convertToTourDetailResponseDto(updatedTour);
         }
         else{
             throw new TourNotFoundException("There is no tour exists with this id : "+id);
         }
    }

    @Override
    public void deleteTour(Long id) {
       Optional<Tour>optional=tourRepo.findById(id);
       if(optional.isPresent()){
           tourRepo.delete(optional.get());
       }
       else{
           throw new TourNotFoundException("There is  no tour exists with this id :"+id );
       }
    }

    @Override
    public List<TourSummaryResponseDto> getToursByCountry(String country) {
        List<Tour>tours=tourRepo.findTourByCountry(country);
        List<TourSummaryResponseDto> responseDtoList=new ArrayList<>();
        for(Tour tour:tours){
            responseDtoList.add(tourMapper.convertToTourSummaryResponseDto(tour));
        }
        return responseDtoList;
    }

    @Override
    public List<TourSummaryResponseDto> getToursByPriceRange(Double minPrice, Double maxPrice) {
        List<Tour>tours=tourRepo.findTourByPriceBetween(minPrice,maxPrice);
        List<TourSummaryResponseDto>responseDtoList=new ArrayList<>();
        for(Tour tour:tours){
        responseDtoList.add(tourMapper.convertToTourSummaryResponseDto(tour));
        }
        return responseDtoList;
    }
}
