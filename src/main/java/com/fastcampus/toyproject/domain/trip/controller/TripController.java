package com.fastcampus.toyproject.domain.trip.controller;


import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripDetailDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripRequestDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripResponseDTO;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member/{memberId}/trip")
public class TripController {

    private final TripService tripService;

    @GetMapping("/trips")
    public ResponseDTO<List<TripResponseDTO>> getAllTrips(){
        return ResponseDTO.ok
            ("모든 Trip들을 가져왔습니다.",tripService.getAllTrips()
            );
    }

    @GetMapping("/trips/{tripId}")
    public ResponseDTO<TripDetailDTO> getTripDetail(
        @PathVariable Long tripId
    ) {
        return ResponseDTO.ok
            ("선택한 Trip의 정보를 가져왔습니다.", tripService.getTripDetail(tripId)
            );
    }

    @PostMapping
    public ResponseDTO<TripResponseDTO> insertTrip(@PathVariable Long memberId,
        @RequestBody TripRequestDTO tripRequestDTO) {
        TripResponseDTO savedTrip = tripService.insertTrip(memberId, tripRequestDTO);
        return ResponseDTO.ok("Trip이 성공적으로 저장되었습니다.", savedTrip);
    }

    @PutMapping("/{tripId}")
    public ResponseDTO<TripResponseDTO> updateTrip(
        @PathVariable Long memberId,
        @PathVariable Long tripId,
        @RequestBody TripRequestDTO tripRequestDTO) {
        TripResponseDTO updatedTrip = tripService.updateTrip(memberId, tripId, tripRequestDTO);
        return ResponseDTO.ok("Trip이 성공적으로 업데이트되었습니다.", updatedTrip);
    }

    @DeleteMapping("/{tripId}")
    public ResponseDTO<Void> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseDTO.ok("Trip이 성공적으로 삭제되었습니다.");
    }
}