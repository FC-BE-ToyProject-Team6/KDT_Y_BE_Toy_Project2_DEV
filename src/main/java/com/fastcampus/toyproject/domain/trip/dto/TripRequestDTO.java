package com.fastcampus.toyproject.domain.trip.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripRequestDTO {

    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDomestic;
}
