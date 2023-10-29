package com.fastcampus.toyproject.domain.trip;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fastcampus.toyproject.domain.trip.controller.TripController;
import com.fastcampus.toyproject.domain.trip.dto.TripRequest;
import com.fastcampus.toyproject.domain.trip.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class TripControllerTest {

    @InjectMocks
    private TripController tripController;

    @Mock
    private TripService tripService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tripController).build();

        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void insertTripTest() throws Exception {
        TripRequest tripRequest = new TripRequest("test 여행", LocalDate.now(),
            LocalDate.now().plusDays(5), true);
        given(tripService.insertTrip(anyLong(), any(TripRequest.class))).willReturn(null);

        mockMvc.perform(post("/api/member/1/trip")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripRequest)))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void updateTripTest() throws Exception {
        TripRequest tripRequest = new TripRequest("Updated Trip", LocalDate.now(),
            LocalDate.now().plusDays(10), false);
        given(tripService.updateTrip(anyLong(), anyLong(), any(TripRequest.class))).willReturn(
            null);

        mockMvc.perform(put("/api/member/1/trip/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripRequest)))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void deleteTripTest() throws Exception {
        given(tripService.deleteTrip(anyLong())).willReturn(null);

        mockMvc.perform(delete("/api/member/1/trip/1"))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
