package com.elseveremirli.server.entities;

import com.elseveremirli.server.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.Set;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Marker implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean isCredentialsNonExpired;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;
}