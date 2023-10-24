package com.fastcampus.toyproject.domain.trip.service;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.trip.dto.TripDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip insertTrip(TripDTO tripDTO) {
        Trip trip = Trip.builder()
            .tripName(tripDTO.getTripName())
            .startDate(tripDTO.getStartDate())
            .endDate(tripDTO.getEndDate())
            .isDomestic(tripDTO.isDomestic())
            .build();

        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, TripDTO tripDTO) {
        Optional<Trip> oldTrip = tripRepository.findById(id);
        if (!oldTrip.isPresent()) {
            throw new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 Trip이 없습니다.");
        }

        Trip existTrip = oldTrip.get();
        existTrip.setTripName(tripDTO.getTripName());
        existTrip.setStartDate(tripDTO.getStartDate());
        existTrip.setEndDate(tripDTO.getEndDate());
        existTrip.setDomestic(tripDTO.isDomestic());

        return tripRepository.save(existTrip);
    }

    public void deleteTrip(Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (!tripOptional.isPresent()) {
            throw new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 Trip이 없습니다.");
        }

        Trip trip = tripOptional.get();
        trip.setIsDeleted(true);
        trip.setDeletedAt(LocalDateTime.now());
        tripRepository.save(trip);
    }
}