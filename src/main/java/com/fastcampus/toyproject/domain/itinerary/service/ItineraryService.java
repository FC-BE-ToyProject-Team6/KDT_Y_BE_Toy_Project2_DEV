package com.fastcampus.toyproject.domain.itinerary.service;

import com.fastcampus.toyproject.domain.itinerary.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
}
