package com.fastcampus.toyproject.domain.trip.controller;

import com.fastcampus.toyproject.common.dto.ErrorResponseDTO;
import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    private static final Logger log = LoggerFactory.getLogger(TripController.class);


    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<?> insertTrip(@RequestBody TripDTO tripDTO) {
        System.out.println(tripDTO);

        try {
            Trip savedTrip = tripService.insertTrip(tripDTO);
            return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 저장되었습니다.", savedTrip));
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorResponseDTO.error(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody TripDTO tripDTO) {
        try {
            Trip updatedTrip = tripService.updateTrip(id, tripDTO);
            if (updatedTrip == null) {
                return new ResponseEntity<>(ErrorResponseDTO.error(), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 업데이트되었습니다.", updatedTrip));
        } catch (Exception e) {
            log.error("Error updating trip", e);
            return new ResponseEntity<>(ErrorResponseDTO.error(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")  // DELETE 요청을 처리하는 메서드
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        try {
            boolean isDeleted = tripService.deleteTrip(id);
            if (!isDeleted) {
                return new ResponseEntity<>(ErrorResponseDTO.error("해당하는 Trip이 없습니다."), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(ResponseDTO.ok("Trip이 성공적으로 삭제되었습니다."));
        } catch (Exception e) {  // 예외 발생 시
            return new ResponseEntity<>(ErrorResponseDTO.error(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}