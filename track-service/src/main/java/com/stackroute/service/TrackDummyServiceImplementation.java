package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
//@Profile("dev")
@Service
@Qualifier("call")
public class TrackDummyServiceImplementation implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackDummyServiceImplementation(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        System.out.println("save track");
        return null;
    }

    @Override
    public List<Track> getAllTracks() {
        System.out.println("retrieve tracks");
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        System.out.println("Retrieve a track");
        return null;
    }

    @Override
    public Track deleteById(int id) throws TrackNotFoundException {
        System.out.println("delete a track");
        return null;
    }

    @Override
    public Track updateTrackbyId(int id, Track track) throws TrackNotFoundException {
        System.out.println("update a track");
        return null;
    }

    @Override
    public Track searchTrackByName(String name) throws TrackNotFoundException {
        System.out.println("search a track");
        return null;
    }
}
