package com.fastcampus.toyproject.domain.trip.repository;

import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByIsDeletedIsFalseOrderByTripId();

    Optional<Trip> findByTripIdAndIsDeletedIsFalse(Long tripId);

}
