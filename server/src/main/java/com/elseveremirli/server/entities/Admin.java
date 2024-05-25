package com.elseveremirli.server.entities;

import com.elseveremirli.server.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity
@Data
@Table(name = "admin")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @UniqueElements
    private String password;
    @NotNull
    @UniqueElements
    private String username;
    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean isCredentialsNonExpired;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "admin_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

}
