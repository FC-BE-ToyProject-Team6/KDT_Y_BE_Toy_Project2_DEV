package com.fastcampus.toyproject.domain.itinerary.controller;

import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryRequest;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    private final ItineraryService itineraryService;

    @PostMapping("/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> insertItineraries(
            @PathVariable final Long tripId,
            @Valid @RequestBody final List<ItineraryRequest> request
    ) {
        return ResponseDTO.ok("여정들 삽입 완료", itineraryService.insertItineraries(tripId, request));
    }

    @DeleteMapping("/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> deleteItineraries(
            @PathVariable final Long tripId,
            @Valid @RequestBody final List<Long> deleteIdList
    ) {
        return ResponseDTO.ok("여정들 삭제 완료", itineraryService.deleteItineraries(tripId, deleteIdList));
    }

}
