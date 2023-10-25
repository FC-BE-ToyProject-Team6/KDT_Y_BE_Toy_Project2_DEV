package com.fastcampus.toyproject.domain.trip.repository;

import com.fastcampus.toyproject.domain.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}