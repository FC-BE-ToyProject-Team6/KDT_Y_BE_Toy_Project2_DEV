package com.fastcampus.toyproject.domain.trip.service;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.NO_ITINERARY;
import static com.fastcampus.toyproject.common.exception.ExceptionCode.NO_SUCH_TRIP;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.domain.itinerary.dto.ItineraryResponse;
import com.fastcampus.toyproject.domain.itinerary.repository.ItineraryRepository;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import com.fastcampus.toyproject.domain.trip.dto.TripDetailDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripRequestDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripResponseDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final MemberRepository memberRepository;
    private final ItineraryRepository itineraryRepository;

    private Member getValidatedMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(
                () -> new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 멤버가 없습니다."));
    }

    private Trip getTripByTripId(Long tripId) {
        return tripRepository
            .findById(tripId)
            .orElseThrow(() -> new DefaultException(NO_SUCH_TRIP));
    }

    private List<ItineraryResponse> getItinerariesByTripId(Long tripId) {
        return itineraryRepository
            .findAllByTripIdAndIsDeletedNull(getTripByTripId(tripId))
            .map(list->list.stream().map(ItineraryResponse::fromEntity).collect(Collectors.toList()))
            .orElseThrow(()->new DefaultException(NO_ITINERARY));
    }

    private String getItineraryNamesByTrip(Trip trip) {
        return itineraryRepository
            .findAllByTripIdAndIsDeletedNull(trip)
            .orElse(new ArrayList<>())
            .stream().map(itinerary -> ItineraryResponse.fromEntity(itinerary).getItineraryName())
            .collect(Collectors.joining(", "));
    }

    @Transactional(readOnly = true)
    public List<TripResponseDTO> getAllTrips() {
        return tripRepository.findAll()
            .stream().map(trip -> TripResponseDTO.fromEntity
                (
                    trip, getItineraryNamesByTrip(trip)
                )
            )
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TripDetailDTO getTripDetail(Long tripId) {
        return TripDetailDTO.fromEntity(getTripByTripId(tripId), getItinerariesByTripId(tripId));
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
        return TripResponseDTO.fromEntity(savedTrip);
    }

    public TripResponseDTO updateTrip(Long memberId, Long tripId, TripRequestDTO tripRequestDTO) {
        Member member = getValidatedMember(memberId);
        Trip existTrip = tripRepository.findById(tripId)
            .orElseThrow(() -> new DefaultException(NO_SUCH_TRIP));

        if (!existTrip.getMember().getMemberId().equals(memberId)) {
            throw new DefaultException(ExceptionCode.INVALID_REQUEST, "멤버의 여행 정보가 일치하지 않습니다.");
        }

        existTrip.updateFromDTO(tripRequestDTO);
        Trip updatedTrip = tripRepository.save(existTrip);
        return TripResponseDTO.fromEntity(updatedTrip);
    }

    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new DefaultException(NO_SUCH_TRIP));

        trip.delete(LocalDateTime.now());
        trip.setIsDeleted(true);
        tripRepository.save(trip);
    }
}