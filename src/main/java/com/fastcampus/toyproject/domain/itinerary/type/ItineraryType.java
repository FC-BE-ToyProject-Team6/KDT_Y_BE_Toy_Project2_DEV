package com.fastcampus.toyproject.domain.itinerary.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItineraryType {
    MOVEMENT("이동"),
    LODGEMENT("숙박"),
    STAY("체류");
    private final String value;

}
