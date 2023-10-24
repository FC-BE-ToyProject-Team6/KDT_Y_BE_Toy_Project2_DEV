package com.fastcampus.toyproject.domain.itinerary.repository;

import com.fastcampus.toyproject.domain.itinerary.entity.Lodgement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LodgementRepository extends JpaRepository<Lodgement, Long> {
}
