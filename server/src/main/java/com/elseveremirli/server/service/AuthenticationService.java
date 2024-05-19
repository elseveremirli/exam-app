package com.elseveremirli.server.service;

import com.elseveremirli.server.dataAccess.UserRepository;
import com.elseveremirli.server.dto.UserDto;
import com.elseveremirli.server.dto.UserLogin;
import com.elseveremirli.server.dto.UserRequest;
import com.elseveremirli.server.dto.UserResponse;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public UserResponse save(UserRequest userRequest) {
        User user = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .accountNonLocked(true)
                .isEnabled(true)
                .accountNonExpired(true)
                .isCredentialsNonExpired(true)
                .authorities(Collections.singleton(Role.ROLE_USER))
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();

    }

    public UserResponse auth(UserLogin userLogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        User user = userRepository.findByUsername(userLogin .getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}