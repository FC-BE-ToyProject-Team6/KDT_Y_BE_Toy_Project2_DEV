package com.fastcampus.toyproject.domain.trip.service;

import com.fastcampus.toyproject.domain.trip.dto.TripDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  // 서비스 클래스임을 나타냅니다.
public class TripService {

    @Autowired  // 리포지토리를 자동으로 주입받습니다.
    private TripRepository tripRepository;



    // Trip 정보를 저장하는 메서드
    public Trip insertTrip(TripDTO tripDTO) {
        System.out.println("isDomestic value from DTO: " + tripDTO.isDomestic());


        Trip trip = Trip.builder()

            .tripName(tripDTO.getTripName())
            .startDate(tripDTO.getStartDate())
            .endDate(tripDTO.getEndDate())
            .isDomestic(tripDTO.isDomestic())
            .build();

        return tripRepository.save(trip);
    }

    // Trip 정보를 업데이트하는 메서드
    public Trip updateTrip(Long id, TripDTO tripDTO) {
        Optional<Trip> oldTrip = tripRepository.findById(id);
        if (oldTrip.isPresent()) {
            Trip existTrip = oldTrip.get();
            existTrip.setTripName(tripDTO.getTripName());
            existTrip.setStartDate(tripDTO.getStartDate());
            existTrip.setEndDate(tripDTO.getEndDate());
            existTrip.setDomestic(tripDTO.isDomestic());

            return tripRepository.save(existTrip);
        }
        return null;
    }


    // Trip 정보를 삭제하는 메서드
    public boolean deleteTrip(Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if(tripOptional.isPresent()){
            Trip trip = tripOptional.get();
            trip.setIsDeleted(true);
            trip.setDeletedAt(LocalDateTime.now());
            tripRepository.save(trip);
            return true;
        }
        return false;
    }

}