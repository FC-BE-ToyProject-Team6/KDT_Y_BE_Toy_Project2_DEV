package com.fastcampus.toyproject.domain.itinerary.controller;

import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryRequest;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
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
            @Valid @RequestBody final List<ItineraryRequest> itineraryRequests
    ) {
        for (ItineraryRequest ir : itineraryRequests) {
            DateUtil.isStartDateEarlierThanEndDate(ir.getStartDate(), ir.getEndDate());
        }
        return ResponseDTO.ok("여정들 삽입 완료", itineraryService.insertItineraries(tripId, itineraryRequests));
    }

    @PutMapping("/delete/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> deleteItineraries(
            @PathVariable final Long tripId,
            @Valid @RequestBody final List<Long> deleteIdList
    ) {
        return ResponseDTO.ok("여정들 삭제 완료", itineraryService.deleteItineraries(tripId, deleteIdList));
    }

    @PutMapping("/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> updateItinerary(
        @PathVariable final Long tripId,
        @Valid @RequestBody List<ItineraryUpdateRequest> itineraryUpdateRequests) {

        for (ItineraryRequest ir : itineraryUpdateRequests) {
            DateUtil.isStartDateEarlierThanEndDate(ir.getStartDate(), ir.getEndDate());
        }

        List<ItineraryResponse> itineraryResponses = itineraryService.updateItineraries(tripId,
            itineraryUpdateRequests);

        return ResponseDTO.ok("여정들 수정 완료", itineraryResponses);
    }
}
