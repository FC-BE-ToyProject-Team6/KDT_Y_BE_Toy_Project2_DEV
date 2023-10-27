package com.fastcampus.toyproject.domain.itinerary.entity;

import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryUpdateRequest;
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

    public void updateMovement(ItineraryUpdateRequest req) {
        super.updateItineraryName(req.getMovementName());
        super.updateItineraryOrder(req.getOrder());
        this.transportation = req.getItem();
        this.departureDate = req.getStartDate();
        this.departurePlace = req.getDeparturePlace();
        this.arrivalDate = req.getEndDate();
        this.arrivalPlace = req.getArrivalPlace();
    }
}
