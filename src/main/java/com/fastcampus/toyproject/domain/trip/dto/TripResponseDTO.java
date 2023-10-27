package com.fastcampus.toyproject.domain.trip.dto;

import com.fastcampus.toyproject.domain.trip.entity.Trip;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponseDTO {

    private Long tripId;
    private Long memberId;
    private String tripName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDomestic;
    private String itineraryNames;

    public static TripResponseDTO fromEntity(Trip trip) {
        return TripResponseDTO.builder()
            .tripId(trip.getTripId())
            .memberId(trip.getMember().getMemberId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .build();
    }

    public static TripResponseDTO fromEntity(Trip trip, String names) {
        return TripResponseDTO.builder()
            .tripId(trip.getTripId())
            .memberId(trip.getMember().getMemberId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .itineraryNames(names)
            .build();
    }

}

