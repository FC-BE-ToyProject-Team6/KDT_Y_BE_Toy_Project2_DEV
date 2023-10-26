package com.fastcampus.toyproject.domain.trip.service;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import com.fastcampus.toyproject.domain.trip.dto.TripRequestDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripResponseDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TripService(TripRepository tripRepository, MemberRepository memberRepository) {
        this.tripRepository = tripRepository;
        this.memberRepository = memberRepository;
    }

    private Member getValidatedMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(
                () -> new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 멤버가 없습니다."));
    }

    private TripResponseDTO convertToResponseDTO(Trip trip) {
        return TripResponseDTO.builder()
            .tripId(trip.getTripId())
            .memberId(trip.getMember().getMemberId())
            .tripName(trip.getTripName())
            .startDate(trip.getStartDate())
            .endDate(trip.getEndDate())
            .isDomestic(trip.getIsDomestic())
            .build();
    }

    public TripResponseDTO insertTrip(Long memberId, TripRequestDTO tripRequestDTO) {
        Member member = getValidatedMember(memberId);
        Trip trip = Trip.builder()
            .member(member)
            .tripName(tripRequestDTO.getTripName())
            .startDate(tripRequestDTO.getStartDate())
            .endDate(tripRequestDTO.getEndDate())
            .isDomestic(tripRequestDTO.getIsDomestic())
            .build();

        Trip savedTrip = tripRepository.save(trip);
        return convertToResponseDTO(savedTrip);
    }

    public TripResponseDTO updateTrip(Long memberId, Long tripId, TripRequestDTO tripRequestDTO) {
        Member member = getValidatedMember(memberId);
        Trip existTrip = tripRepository.findById(tripId)
            .orElseThrow(() -> new DefaultException(ExceptionCode.NO_SUCH_TRIP));

        if (!existTrip.getMember().getMemberId().equals(memberId)) {
            throw new DefaultException(ExceptionCode.INVALID_REQUEST, "멤버의 여행 정보가 일치하지 않습니다.");
        }

        existTrip.updateFromDTO(tripRequestDTO);
        Trip updatedTrip = tripRepository.save(existTrip);
        return convertToResponseDTO(updatedTrip);
    }

    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new DefaultException(ExceptionCode.NO_SUCH_TRIP));

        trip.delete(LocalDateTime.now());
        trip.setIsDeleted(true);
        tripRepository.save(trip);
    }
}