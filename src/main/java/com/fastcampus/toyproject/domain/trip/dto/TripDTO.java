package com.fastcampus.toyproject.domain.trip.dto;

import com.fastcampus.toyproject.domain.trip.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor

@NoArgsConstructor
/* TripDTO도 response, request 분리하면 어떨까요 ex) 중첩 클래스 or 여정처럼 각각 클래스*/

public class TripDTO {

    private Long tripId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDomestic;
    private String itineraryNames;


    //response로 분리(response 할 때는 Trip id 반환 안 했음)
    public static TripDTO fromEntity(Trip trip, String names) {
        return TripDTO.builder()
            .tripId(trip.getTripId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .itineraryNames(names)
            .build();
    }
}
