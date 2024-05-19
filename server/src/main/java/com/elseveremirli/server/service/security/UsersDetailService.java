package com.elseveremirli.server.service.security;

import com.elseveremirli.server.dataAccess.MarkerRepository;
import com.elseveremirli.server.dataAccess.UserRepository;
import com.elseveremirli.server.entities.Marker;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.service.MarkerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UsersDetailService implements UserDetailsService {

    private final MarkerRepository markerRepository;
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Marker> marker = markerRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.orElseThrow(EntityNotFoundException::new);
        }
        return marker.orElseThrow(EntityNotFoundException::new);
    }
}
