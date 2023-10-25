package com.fastcampus.toyproject.domain.itinerary.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryRequest {

    @NotNull
    @Range(min = 1, max = 3)
    private Integer type;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Min(1)
    private Integer order;

    private String departurePlace;
    private String arrivalPlace;

}
