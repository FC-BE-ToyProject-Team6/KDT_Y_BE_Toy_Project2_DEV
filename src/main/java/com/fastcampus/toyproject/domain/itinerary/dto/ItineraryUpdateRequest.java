package com.fastcampus.toyproject.domain.itinerary.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryUpdateRequest {

    @NotNull
    private Long itineraryId;

    @NotNull
    @Range(min = 1, max = 3)
    private Integer type;

    private String itineraryName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotNull
    @Min(1)
    private Integer itineraryOrder;

    private String departurePlace;

    private String arrivalPlace;

}
