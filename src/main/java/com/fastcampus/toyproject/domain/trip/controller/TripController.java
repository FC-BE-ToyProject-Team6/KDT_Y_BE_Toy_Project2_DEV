package com.fastcampus.toyproject.domain.trip.controller;


import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private static final Logger log = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Trip>> insertTrip(@RequestBody TripDTO tripDTO) {
        Trip savedTrip = tripService.insertTrip(tripDTO);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 저장되었습니다.", savedTrip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Trip>> updateTrip(@PathVariable Long id,
        @RequestBody TripDTO tripDTO) {
        Trip updatedTrip = tripService.updateTrip(id, tripDTO);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 업데이트되었습니다.", updatedTrip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 삭제되었습니다."));
    }
}