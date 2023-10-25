package com.fastcampus.toyproject.domain.trip.controller;


import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip/{memberId}")
public class TripController {


    @Autowired //final&request로 바꾸고 Autowired 안 해도 되지 않을까요?
    private TripService tripService;

    @GetMapping("/trips")
    public ResponseDTO<List<TripDTO>> getAllTrips(){
        return ResponseDTO.ok("모든 Trip들을 가져왔습니다.",tripService.getAllTrips());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Trip>> insertTrip(@PathVariable Long memberId,
        @RequestBody TripDTO tripDTO) {
        Trip savedTrip = tripService.insertTrip(memberId, tripDTO);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 저장되었습니다.", savedTrip));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<ResponseDTO<Trip>> updateTrip(
        @PathVariable Long memberId,
        @PathVariable Long tripId,
        @RequestBody TripDTO tripDTO) {
        Trip updatedTrip = tripService.updateTrip(memberId, tripId, tripDTO);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 업데이트되었습니다.", updatedTrip));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 삭제되었습니다."));
    }
}