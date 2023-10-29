package com.fastcampus.toyproject.domain.itinerary.controller;

import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryRequest;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 여정과 관련된 Itinerary Rest Controller itinerary 삽입, 수정, 삭제 기능
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/member/{memberId}/trip-itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> insertItineraries(
        @PathVariable final Long tripId,
        @Valid @RequestBody final List<ItineraryRequest> itRequestList
    ) {
        // 출발 시간 < 도착 시간인지 확인
        for (ItineraryRequest ir : itRequestList) {
            DateUtil.isStartDateEarlierThanEndDate(
                ir.getStartDate(),
                ir.getEndDate()
            );
        }
        return ResponseDTO.ok("여정들 삽입 완료",
            itineraryService.insertItineraries(tripId, itRequestList)
        );
    }

    @PutMapping("/delete/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> deleteItineraries(
        @PathVariable final Long tripId,
        @Valid @RequestBody final List<Long> itineraryIdList
    ) {
        return ResponseDTO.ok("여정들 삭제 완료",
            itineraryService.deleteItineraries(tripId, itineraryIdList)
        );
    }

    @PutMapping("/{tripId}")
    public ResponseDTO<List<ItineraryResponse>> updateItineraries(
        @PathVariable final Long tripId,
        @Valid @RequestBody final List<ItineraryUpdateRequest> itUpdateRequestList) {

        // 출발 시간 < 도착 시간인지 확인
        for (ItineraryRequest ir : itUpdateRequestList) {
            DateUtil.isStartDateEarlierThanEndDate(
                ir.getStartDate(),
                ir.getEndDate()
            );
        }

        return ResponseDTO.ok("여정들 수정 완료",
            itineraryService.updateItineraries(tripId, itUpdateRequestList)
        );
    }
}
