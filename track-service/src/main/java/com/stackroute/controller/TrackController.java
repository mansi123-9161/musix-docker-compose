package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {
    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistException {
        Track savedTrack = trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.CREATED);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackByID(@PathVariable int id) throws TrackNotFoundException {
        Track trackDetails = trackService.getTrackById(id);
        System.out.println(trackDetails);
        return new ResponseEntity<>(trackDetails, HttpStatus.FOUND);
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks() {
        try {
            return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) throws TrackNotFoundException {
        Track trackRemoved = trackService.deleteById(id);
        return new ResponseEntity<>(trackRemoved, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("track/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody Track track) throws TrackNotFoundException {
        Track trackUpdated = trackService.updateTrackbyId(id, track);
        return new ResponseEntity<>(trackUpdated, HttpStatus.ACCEPTED);
    }

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name) throws TrackNotFoundException {
        Track trackDetails = trackService.searchTrackByName(name);
        return new ResponseEntity<>(trackDetails, HttpStatus.FOUND);
    }
}
