package com.fastcampus.toyproject.domain.trip.controller;


import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripRequestDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripResponseDTO;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member/{memberId}/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<TripResponseDTO>> insertTrip(@PathVariable Long memberId,
        @RequestBody TripRequestDTO tripRequestDTO) {
        TripResponseDTO savedTrip = tripService.insertTrip(memberId, tripRequestDTO);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 저장되었습니다.", savedTrip));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<ResponseDTO<TripResponseDTO>> updateTrip(
        @PathVariable Long memberId,
        @PathVariable Long tripId,
        @RequestBody TripRequestDTO tripRequestDTO) {
        TripResponseDTO updatedTrip = tripService.updateTrip(memberId, tripId, tripRequestDTO);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 업데이트되었습니다.", updatedTrip));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<ResponseDTO<Void>> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 삭제되었습니다."));
    }
}