package com.fastcampus.toyproject.domain.itinerary.service;

import com.fastcampus.toyproject.domain.itinerary.repository.ItineraryRepository;
import com.fastcampus.toyproject.domain.itinerary.repository.LodgementRepository;
import com.fastcampus.toyproject.domain.itinerary.repository.MovementRepository;
import com.fastcampus.toyproject.domain.itinerary.repository.StayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final LodgementRepository lodgementRepository;
    private final MovementRepository movementRepository;
    private final StayRepository stayRepository;
}
