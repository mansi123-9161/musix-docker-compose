package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;

    //    Creating a mock for TrackRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    TrackServiceImplement trackServiceImplement;
    List<Track> list = null;

    @Before
    public void setup() {
//        Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(1);
        track.setName("Bekhayali");
        track.setComment("From movie Kabir singh");
        List<Track> tracklist = new ArrayList<>();
        tracklist.add(track);
    }

    //    Track getting saved success
    @Test
    public void givenTrackDetailsShouldGetSaved() throws TrackAlreadyExistException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackServiceImplement.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

//        To verify that Track Repository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    //    Retrieving all tracks success
    @Test
    public void givenUrlShouldRetrieveAllTracks() {
        trackRepository.save(track);
//        stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracks = trackServiceImplement.getAllTracks();
        Assert.assertEquals(list, tracks);
//        To verify that Track Repository get all tracks method is only called once
        verify(trackRepository, times(1)).findAll();
    }

    //    Retrieve track by name success
    @Test
    public void givenTrackNameShouldReturnTrackDetails() throws TrackNotFoundException {
        trackRepository.save(track);
//        stubbing the mock to return specific data
        when(trackRepository.findByName("Bekhayali")).thenReturn(track);
        Track getTrack = trackServiceImplement.searchTrackByName("Bekhayali");
        Assert.assertEquals(track, getTrack);
//        To verify that  Track Repository get track by name  method is only called once
        verify(trackRepository, times(1)).findByName("Bekhayali");
    }

    //    Retrieve track by id success
    @Test
    public void givenTrackIdShouldReturnTrackDetails() throws TrackNotFoundException {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findById(1)).thenReturn(Optional.of(track));
        Optional<Track> getTrack = trackRepository.findById(1);
        System.out.println(getTrack.toString());
        Assert.assertEquals(track, getTrack.get());
        verify(trackRepository, times(1)).findById(1);
    }
}