package com.elseveremirli.server.service;

import com.elseveremirli.server.repository.AdminRepository;
import com.elseveremirli.server.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.elseveremirli.server.dto.Admin.AdminRequest;
import com.elseveremirli.server.dto.Admin.AdminResponse;
import com.elseveremirli.server.enums.Role;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;



    public AdminResponse saveAdmin(AdminRequest adminRequest) {
        Admin admin = Admin.builder()
                .username(adminRequest.getUsername())
                .password(passwordEncoder.encode(adminRequest.getPassword()) )
                .accountNonLocked(true)
                .isEnabled(true)
                .accountNonExpired(true)
                .isCredentialsNonExpired(true)
                .authorities(Collections.singleton(Role.ROLE_ADMIN))
                .build();
        adminRepository.save(admin);
        var token = jwtService.generateToken(admin);
        return AdminResponse.builder().token(token).build();
    }



    public AdminResponse loginAdmin(AdminRequest adminRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminRequest.getUsername(), adminRequest.getPassword()));
        Admin admin = adminRepository.findByUsername(adminRequest .getUsername()).orElseThrow();
        String token = jwtService.generateToken(admin);
        return AdminResponse.builder().token(token).build();
    }
}
