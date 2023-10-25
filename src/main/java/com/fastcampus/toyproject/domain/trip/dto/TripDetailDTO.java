package com.fastcampus.toyproject.domain.trip.dto;

import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TripDetailDTO {

    private Long tripId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDomestic;
    private List<Itinerary> itineraries;

    public static TripDetailDTO fromEntity(Trip trip, List<Itinerary> itineraryList) {
        return TripDetailDTO.builder()
            .tripId(trip.getTripId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .itineraries(itineraryList)
            .build();
    }
}
