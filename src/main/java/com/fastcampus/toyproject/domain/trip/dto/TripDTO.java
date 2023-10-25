package com.fastcampus.toyproject.domain.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private Long memberId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDomestic;




}
