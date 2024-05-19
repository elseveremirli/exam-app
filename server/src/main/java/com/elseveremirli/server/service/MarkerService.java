package com.elseveremirli.server.service;

import com.elseveremirli.server.dataAccess.MarkerRepository;
import com.elseveremirli.server.entities.Marker;
import com.elseveremirli.server.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarkerService   {
    private final MarkerRepository markerRepository;


    public MarkerService(MarkerRepository markerRepository) {
        this.markerRepository = markerRepository;
    }

    public Optional<Marker> findByUsername(String username){
        return markerRepository.findByUsername(username);
    };
}
