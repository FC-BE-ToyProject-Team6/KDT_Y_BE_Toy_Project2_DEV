package com.fastcampus.toyproject.domain.trip.dto;

import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * trip 전체 조회시 사용하는 tripResponse
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

    private Long tripId;
    private Long memberId;
    private String tripName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDomestic;
    private String itineraryNameList;
    private String tripPeriod;

    public static TripResponse fromEntity(Trip trip) {
        return TripResponse.builder()
            .tripId(trip.getTripId())
            .memberId(trip.getMember().getMemberId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .itineraryNameList(
                Optional.ofNullable(trip.getItineraryList())
                    .orElse(new ArrayList<>())
                    .stream()
                    .map(Itinerary::getItineraryName)
                    .collect(Collectors.joining(", ")))
            .tripPeriod(DateUtil.getDaysBetweenDate(trip.getStartDate(), trip.getEndDate()))
            .build();
    }

}

