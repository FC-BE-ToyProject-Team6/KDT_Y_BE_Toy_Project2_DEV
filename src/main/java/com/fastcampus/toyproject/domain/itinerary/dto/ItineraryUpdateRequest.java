package com.fastcampus.toyproject.domain.itinerary.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ItineraryUpdateRequest extends ItineraryRequest {

    @NotNull
    private Long itineraryId;


}
