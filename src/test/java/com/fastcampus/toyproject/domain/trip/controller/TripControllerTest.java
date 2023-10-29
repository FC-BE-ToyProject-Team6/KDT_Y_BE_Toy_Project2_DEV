package com.fastcampus.toyproject.domain.trip.controller;

import com.fastcampus.toyproject.domain.trip.service.TripService;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TripController.class)
class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    protected MediaType contentType =
        new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    /*@Test
    void getAllTrips() throws Exception {
        TripDetailDTO firstTripDTO = TripDetailDTO.builder()
            .tripName("제주도")
            .startDate(LocalDateTime.of(2023,9,12,8,30,00))
            .endDate(LocalDateTime.of(2023,10,12,23,10,20))
            .isDomestic(Boolean.FALSE)
            .build();

        TripDetailDTO secondTripDTO = TripDetailDTO.builder()
            .tripName("대만")
            .startDate(LocalDateTime.of(2023,9,12,8,30,00))
            .endDate(LocalDateTime.of(2023,10,12,23,10,20))
            .isDomestic(Boolean.TRUE)
            .build();

        given(tripService.getAllTrips())
            .willReturn(Arrays.asList(firstTripDTO,secondTripDTO));

        mockMvc.perform(get("/api/trip/1/trips").contentType(contentType))
            .andExpect(status().isOk())
            .andExpect(
                jsonPath("$.[0].tripName", is("제주도"))
            )
            .andExpect(
                jsonPath("$.[0].isDomestic", is(Boolean.FALSE))
            )
            .andExpect(
                jsonPath("$.[1].tripName", is("대만"))
            )
            .andExpect(
                jsonPath("$.[1].isDomestic", is(Boolean.TRUE))
            );
    }*/
}