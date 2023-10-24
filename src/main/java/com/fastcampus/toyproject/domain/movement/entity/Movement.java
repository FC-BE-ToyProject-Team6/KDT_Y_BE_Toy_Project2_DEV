package com.fastcampus.toyproject.domain.movement.entity;

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
public class Movement {

    @Id
    private Long id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departurePlace;
    private String arrivalPlace;
}
