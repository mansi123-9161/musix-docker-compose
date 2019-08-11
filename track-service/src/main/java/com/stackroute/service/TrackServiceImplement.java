package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Profile("prod")
@Service

//Primary annotation is used to give higher preference to a bean when there are multiple beans of the same type.
@Primary
public class TrackServiceImplement implements TrackService {
    private TrackRepository trackRepository;
    @Autowired
    public TrackServiceImplement(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistException("Track is already present");
        }
        Track saveTrack = trackRepository.save(track);
        return saveTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track with given ID does not exist");
        }
        return trackRepository.findById(id).get();
    }

    @Override
    public Track deleteById(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track with given ID does not exist");
        }
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return optionalTrack.get();
    }

    @Override
    public Track updateTrackbyId(int id, Track track) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("Track to be updated not found");
        }
        trackRepository.deleteById(id);
        Track updateTrack = trackRepository.save(track);
        return updateTrack;
    }

    @Override
    public Track searchTrackByName(String name) throws TrackNotFoundException {
        Track retrieveTrack = trackRepository.findByName(name);
        if (retrieveTrack != null) {
            return retrieveTrack;
        } else throw new TrackNotFoundException("This track does not exist");
    }
}
