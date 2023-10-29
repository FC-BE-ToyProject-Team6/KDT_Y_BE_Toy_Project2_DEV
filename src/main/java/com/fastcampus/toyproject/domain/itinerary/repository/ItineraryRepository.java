package com.fastcampus.toyproject.domain.itinerary.repository;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

}
