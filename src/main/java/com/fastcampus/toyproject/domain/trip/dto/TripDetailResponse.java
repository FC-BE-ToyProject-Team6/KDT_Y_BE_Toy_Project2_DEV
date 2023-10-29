package com.fastcampus.toyproject.domain.trip.dto;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponseFactory;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * trip 상세 조회시 사용할 tripDetailResponse
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDetailResponse {

    private Long tripId;
    private String tripName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDomestic;
    private List<ItineraryResponse> itineraryList;

    public static TripDetailResponse fromEntity(Trip trip) {
        return TripDetailResponse.builder()
            .tripId(trip.getTripId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .itineraryList(
                Optional.ofNullable(trip.getItineraryList())
                    .orElse(new ArrayList<>())
                    .stream()
                    .map(ItineraryResponseFactory::getItineraryResponse)
                    .collect(Collectors.toList()))
            .build();
    }
}
