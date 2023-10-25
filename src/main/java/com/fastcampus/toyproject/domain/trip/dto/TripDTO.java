// TripDTO
package com.fastcampus.toyproject.domain.trip.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripDTO {

    private Long memberId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isDomestic;


}
