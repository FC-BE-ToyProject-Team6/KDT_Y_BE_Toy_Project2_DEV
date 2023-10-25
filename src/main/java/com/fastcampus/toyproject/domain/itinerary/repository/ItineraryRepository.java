package com.fastcampus.toyproject.domain.itinerary.repository;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    Optional<List<Itinerary>> findAllByTripIdAndIsDeletedNull(Trip tripId);

}
