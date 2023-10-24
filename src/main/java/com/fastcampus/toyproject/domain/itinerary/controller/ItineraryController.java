package com.fastcampus.toyproject.domain.itinerary.controller;

import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    private final ItineraryService itineraryService;

}
