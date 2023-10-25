package com.fastcampus.toyproject.domain.trip.service;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import com.fastcampus.toyproject.domain.trip.dto.TripDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member getValidatedMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(
                () -> new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 멤버가 없습니다."));
    }

    public Trip insertTrip(Long memberId, TripDTO tripDTO) {
        Member member = getValidatedMember(memberId);

        Trip trip = Trip.builder()
            .member(member)
            .tripName(tripDTO.getTripName())
            .startDate(tripDTO.getStartDate())
            .endDate(tripDTO.getEndDate())
            .isDomestic(tripDTO.getIsDomestic())
            .build();

        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long memberId, Long tripId, TripDTO tripDTO) {
        Member member = getValidatedMember(memberId);

        Trip existTrip = tripRepository.findById(tripId)
            .orElseThrow(() -> new DefaultException(ExceptionCode.NO_SUCH_TRIP));

        if (!existTrip.getMember().getMemberId().equals(memberId)) {
            throw new DefaultException(ExceptionCode.INVALID_REQUEST, "멤버의 여행 정보가 일치하지 않습니다.");
        }

        existTrip.updateFromDTO(tripDTO);
        return tripRepository.save(existTrip);
    }

    public void deleteTrip(Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (!tripOptional.isPresent()) {
            throw new DefaultException(ExceptionCode.NO_SUCH_TRIP);
        }

        Trip trip = tripOptional.get();
        trip.setIsDeleted(true);
        trip.delete(LocalDateTime.now());
        tripRepository.save(trip);
    }
}