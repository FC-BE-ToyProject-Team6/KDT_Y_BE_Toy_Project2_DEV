package com.fastcampus.toyproject.domain.itinerary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository<Itinerary> extends JpaRepository<Itinerary, Long> {
}
