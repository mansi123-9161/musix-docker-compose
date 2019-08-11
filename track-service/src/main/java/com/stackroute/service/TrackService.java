package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    //    methods to operate:
    //    to save a track and trow exception message if same track already present
    public Track saveTrack(Track track) throws TrackAlreadyExistException;

    //    to get all the tracks
    public List<Track> getAllTracks();

    //    to get track by id and to throw exception if track not found
    public Track getTrackById(int id) throws TrackNotFoundException;

    //    to delete by id and to throw exception if track not found
    public Track deleteById(int id) throws TrackNotFoundException;

    //    to update a track and also throw exception
    public Track updateTrackbyId(int id, Track track) throws TrackNotFoundException;

    //    to retrieve a track by name and also throw exception
    public Track searchTrackByName(String name) throws TrackNotFoundException;
}
