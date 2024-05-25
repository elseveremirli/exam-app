package com.elseveremirli.server.service.security;

import com.elseveremirli.server.repository.AdminRepository;
import com.elseveremirli.server.repository.MarkerRepository;
import com.elseveremirli.server.repository.UserRepository;
import com.elseveremirli.server.entities.Admin;
import com.elseveremirli.server.entities.Marker;
import com.elseveremirli.server.entities.User;
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
    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Marker> marker = markerRepository.findByUsername(username);
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.orElseThrow(EntityNotFoundException::new);
        }else if (admin.isPresent()) {
            return admin.orElseThrow(EntityNotFoundException::new);
        }
        return marker.orElseThrow(EntityNotFoundException::new);
    }
}
