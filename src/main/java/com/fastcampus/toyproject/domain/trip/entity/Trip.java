package com.fastcampus.toyproject.domain.trip.entity;

import com.fastcampus.toyproject.domain.member.entity.Member;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue
    private Long tripId;

    @ManyToOne
    private Member memberId;
    private String tripName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isDomestic;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
