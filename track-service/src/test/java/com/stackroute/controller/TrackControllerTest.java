package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.GlobalException;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;
    private List<Track> trackList;

    @Before
    public void setup() throws TrackAlreadyExistException {
//        Initialising the mock object
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController)
                .setControllerAdvice(new GlobalException()).build();
        track = new Track();
        track.setId(1);
        track.setName("Bekhayali");
        track.setComment("From movie Kabir singh");
        trackList = new ArrayList<>();
        trackList.add(track);
    }

    //    save track success
    @Test
    public void givenTrackDetailsShouldGetsaved() throws Exception {
        when(trackService.saveTrack(track)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    //    save track failure
    @Test
    public void givenSavedTrackDetailsShouldThrowException() throws Exception {
        when(trackService.saveTrack(track)).thenThrow(TrackAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }

    //    get all tracks success
    @Test
    public void givenUrlShouldRetrieveAllTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(trackList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    //    get all tracks failure
    @Test
    public void givenUrlShouldThrowException() throws Exception {
        when(trackService.getAllTracks());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }

    //    get track by id success
    @Test
    public void givenIdShouldReturnTrackOfThatId() throws Exception {
        trackService.saveTrack(track);
        when(trackService.getTrackById(1)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    //    get track by id failure
    @Test
    public void givenNotSavedIdShouldThrowException() throws Exception {
        trackService.saveTrack(track);
        when(trackService.getTrackById(1)).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    //    get track by name success
    @Test
    public void givenTrackNameShouldReturnTrackDetails() throws Exception {
        trackService.saveTrack(track);
        when(trackService.searchTrackByName("Bekhayali")).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/Bekhayali")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(track)))
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}