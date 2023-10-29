package com.fastcampus.toyproject.domain.trip.entity;

import com.fastcampus.toyproject.common.BaseTimeEntity;
import com.fastcampus.toyproject.domain.itinerary.entity.Itinerary;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.trip.dto.TripRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Trip extends BaseTimeEntity {

    @OneToMany(mappedBy = "trip",
        cascade = {CascadeType.ALL},
        fetch = FetchType.EAGER,
        orphanRemoval = true)
    List<Itinerary> itineraryList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long tripId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    @JsonIgnore
    private Member member;
    @Column(nullable = false)
    private String tripName;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @ColumnDefault("true")
    private Boolean isDomestic;
    @ColumnDefault("false")
    private Boolean isDeleted;
    //CascadeType.ALL -> 상위 객체 작업 하위객체 모두한테 전파.
    //fetch = FetchType.EAGER -> 실제 조회할 때 한방 쿼리로 다 조회.(itinerary을 사용할 때 쿼리 안나가도 된다.)
    //orphanRemoval = true -> 부모가 자식에 대한 참조를 끊을 때, 참조가 끊어진 자식 Entity(고아 객체)를 DB에서 삭제하도록 설정할 수 있다.
    //우리는 soft delete 이므로 굳이 필요는 없음.

    public void delete() {
        super.delete(LocalDateTime.now());
        this.isDeleted = true;
    }

    public void updateFromDTO(TripRequest tripDTO) {
        this.tripName = tripDTO.getTripName();
        this.startDate = tripDTO.getStartDate();
        this.endDate = tripDTO.getEndDate();
        this.isDomestic = tripDTO.getIsDomestic();
    }


}
