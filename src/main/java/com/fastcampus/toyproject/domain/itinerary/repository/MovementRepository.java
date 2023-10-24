package com.fastcampus.toyproject.domain.itinerary.repository;

import com.fastcampus.toyproject.domain.itinerary.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
}
