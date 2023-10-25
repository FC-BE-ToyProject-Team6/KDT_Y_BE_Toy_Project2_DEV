package com.fastcampus.toyproject.domain.itinerary.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Movement extends Itinerary {

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;

    @Column(nullable = false)
    private String departurePlace;

    @Column(nullable = false)
    private String arrivalPlace;

    @Column(nullable = false)
    private String transportation;

    public void updateDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void updateArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void updateDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public void updateArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }
}
