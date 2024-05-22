package com.elseveremirli.server.entities;
import com.elseveremirli.server.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @UniqueElements
    private String username;
    @NotNull
    @UniqueElements
    @Email
    private String email;
    @NotNull
    private String password;

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean isCredentialsNonExpired;



    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;


    @OneToMany(mappedBy = "user")
    private List<ExamResult> examResults;
}