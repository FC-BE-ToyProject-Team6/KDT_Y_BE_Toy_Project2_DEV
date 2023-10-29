package com.fastcampus.toyproject.domain.trip.repository;

import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByIsDeletedIsFalseOrderByTripId();

    @Query("SELECT DISTINCT t FROM Trip t INNER join FETCH t.itineraryList i WHERE i.isDeleted != true AND t.tripId = :tripId")
    Optional<Trip> findByTripIdAndItineraryDeletedIsFalse(@Param("tripId") Long tripId);

}
