package com.elseveremirli.server.service;

import com.elseveremirli.server.repository.MarkerRepository;
import com.elseveremirli.server.dto.marker.MarkerLogin;
import com.elseveremirli.server.dto.marker.MarkerRequest;
import com.elseveremirli.server.dto.marker.MarkerResponse;
import com.elseveremirli.server.entities.Marker;
import com.elseveremirli.server.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class MarkerService   {

    private final MarkerRepository markerRepository;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;



    public Optional<Marker> findByUsername(String username){
        return markerRepository.findByUsername(username);
    };

    public MarkerResponse saveMarker(MarkerRequest markerRequest) {
        Marker marker = Marker.builder()
                .name(markerRequest.getName())
                .surname(markerRequest.getSurname())
                .username(markerRequest.getUsername())
                .password(passwordEncoder.encode(markerRequest.getPassword()))
                .accountNonLocked(true)
                .isEnabled(true)
                .accountNonExpired(true)
                .isCredentialsNonExpired(true)
                .authorities(Collections.singleton(Role.ROLE_MARKER))
                .build();
        markerRepository.save(marker);
        var token = jwtService.generateToken(marker);
        return MarkerResponse.builder().token(token).build();
    }


    public MarkerResponse loginMarker(MarkerLogin markerLogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(markerLogin.getUsername(), markerLogin.getPassword()));
        Marker marker = markerRepository.findByUsername(markerLogin .getUsername()).orElseThrow();
        String token = jwtService.generateToken(marker);
        return MarkerResponse.builder().token(token).build();
    }
}
