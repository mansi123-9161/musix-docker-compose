package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
//  JUnit will invoke the class to run the tests in that class instead of the runner built into JUnit.

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setup() {
        track = new Track();
        track.setId(1);
        track.setName("Bekhayali");
        track.setComment("From movie Kabir singh");
    }

    @After
    public void teardown() {
        trackRepository.deleteAll();
    }

    //    details getting saved success
    @Test
    public void givenDetailsShouldGetSaved() {
        Track trackInput = new Track(2, "Zara Zara", "RHTDM");
        trackRepository.save(trackInput);
        List<Track> trackDetails = trackRepository.findAll();
        List<Track> expectedDtails = new ArrayList<>();
        expectedDtails.add(trackInput);
        assertEquals(expectedDtails, trackDetails);
    }

    //    details getting saved failure
    @Test
    public void givenDetailsShouldNotGetSaved() {
        Track trackInput = new Track(2, "Zara Zara", "RHTDM");
        trackRepository.save(trackInput);
        List<Track> trackDetails = trackRepository.findAll();
        List<Track> expectedDtails = new ArrayList<>();
        expectedDtails.add(track);
        assertNotEquals(expectedDtails, trackDetails);
    }

    //    Fetching correct id success
    @Test
    public void givenTrackShouldReturnCorrectId() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(1, trackDetails.getId());
    }

    //    fetching correct id failure
    @Test
    public void givenTrackShouldNotFetchCorrectId() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findById(track.getId()).get();
        Assert.assertNotEquals(3, trackDetails.getId());
    }

    //    Retrieve track by id success
    @Test
    public void givenIdShouldReturnTrackDetails() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findById(1).get();
        Track expectedDetails = new Track(1, "Bekhayali", "From movie Kabir singh");
        assertEquals(expectedDetails, trackDetails);
    }

//    Retrieve track by name success
    @Test
    public void givenNameShouldReturnTrackDetails() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findByName("Bekhayali");
        Track expectedDetails = new Track(1, "Bekhayali", "From movie Kabir singh");
        assertEquals(expectedDetails, trackDetails);
    }

    //    Retrieve track by name failure
    @Test
    public void givenNonExistNameShouldNotReturnAnything() {
        trackRepository.save(track);
        Track trackDetails = trackRepository.findByName("i");
        Track expectedDetails = new Track(1, "Bekhayali", "From movie Kabir singh");
        assertNotSame(expectedDetails, trackDetails);
    }
}