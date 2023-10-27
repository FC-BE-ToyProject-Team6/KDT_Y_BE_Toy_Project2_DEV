package com.fastcampus.toyproject.domain.trip.service;

import static com.fastcampus.toyproject.common.exception.ExceptionCode.NO_SUCH_TRIP;

import com.fastcampus.toyproject.common.exception.DefaultException;
import com.fastcampus.toyproject.common.exception.ExceptionCode;
import com.fastcampus.toyproject.common.util.DateUtil;
import com.fastcampus.toyproject.domain.itinerary.service.ItineraryService;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import com.fastcampus.toyproject.domain.trip.dto.TripDetailDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripRequestDTO;
import com.fastcampus.toyproject.domain.trip.dto.TripResponseDTO;
import com.fastcampus.toyproject.domain.trip.entity.Trip;
import com.fastcampus.toyproject.domain.trip.repository.TripRepository;
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
    private final ItineraryService itineraryService;

    private Member getValidatedMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(
                () -> new DefaultException(ExceptionCode.INVALID_REQUEST, "해당하는 멤버가 없습니다."));
    }

    /**
     * trip 아이디를 통한 trip 객체 반환하는 메소드
     * @param tripId
     * @return trip
     */
    public Trip getTripByTripId(Long tripId) {
        return tripRepository
            .findById(tripId)
            .orElseThrow(() -> new DefaultException(NO_SUCH_TRIP));
    }

    /**
     * trip 객체로 연관된 itinerary들의 이름만 반환하는 메소드
     * @param trip
     * @return string
     */
    public String getItineraryNamesByTrip(Trip trip) {

        return itineraryService.getItineraryListByTrip(trip)
                .stream().map(it -> it.getItineraryName())
                .collect(Collectors.joining(", "));
    }

    /**
     * trip 전부 반환하는 메소드
     * @return List<TripResponseDTO>
     */
    @Transactional(readOnly = true)
    public List<TripResponseDTO> getAllTrips() {
        return tripRepository.findAll()
            .stream().map(trip -> TripResponseDTO.fromEntity(
                    trip, getItineraryNamesByTrip(trip)
                )
            )
            .collect(Collectors.toList());
    }

    /**
     * trip과 연관된 itinerary 리스트 반환 (여행 상세 조회)
     * @param tripId
     * @return tripDetailDTO
     */
    @Transactional(readOnly = true)
    public TripDetailDTO getTripDetail(Long tripId) {
        Trip trip = getTripByTripId(tripId);
        return TripDetailDTO.fromEntity(
                trip,
                itineraryService.getItineraryListByTrip(trip)
        );
    }

    /**
     * trip 1개 삽입하는 메소드
     * @param memberId
     * @param tripRequestDTO
     * @return tripResponseDTO
     */
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

    /**
     * trip 수정하는 메소드
     * @param memberId
     * @param tripId
     * @param tripRequestDTO
     * @return tripResponseDTO
     */
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

    /**
     * trip 삭제 및 연관된 itinerary 삭제하는 메소드
     * @param tripId
     */
    public void deleteTrip(Long tripId) {
        Trip trip = getTripByTripId(tripId);
        trip.delete();
        itineraryService.deleteAllItineraryByTrip(trip);
        tripRepository.save(trip);
    }
}