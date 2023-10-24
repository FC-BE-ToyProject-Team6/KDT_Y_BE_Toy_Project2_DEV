package com.fastcampus.toyproject.domain.itinerary.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stay {

    @Id
    private Long id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

}
