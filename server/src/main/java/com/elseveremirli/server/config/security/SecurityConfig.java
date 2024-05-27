package com.elseveremirli.server.config.security;
import com.elseveremirli.server.enums.Role;
import com.elseveremirli.server.service.security.UsersDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter authFilter;
    private final UsersDetailService usersDetailService;
    private final BCryptPasswordEncoder passwordEncoderconfig;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usersDetailService);
        authProvider.setPasswordEncoder(passwordEncoderconfig);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(withDefaults())
                .authorizeHttpRequests(x -> x
                        .requestMatchers("/swagger-ui.html**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
                        .permitAll()
                        .requestMatchers("/api/admin/register")
                        .permitAll()
                        .requestMatchers("/api/marker/register")
                        .hasRole(Role.ROLE_ADMIN.getValue())
                        .requestMatchers("/api/user/register")
                        .permitAll()
                        .requestMatchers("/api/admin/login")
                        .permitAll()
                        .requestMatchers("/api/marker/login")
                        .permitAll()
                        .requestMatchers("/api/user/login")
                        .permitAll()
                        .requestMatchers("/api/marker/**")
                        .hasRole(Role.ROLE_MARKER.getValue())
                        .requestMatchers("/api/user/**")
                        .hasRole(Role.ROLE_USER.getValue())
                        .requestMatchers("/api/exam/save")
                        .hasRole(Role.ROLE_ADMIN.getValue())
                        .requestMatchers("/api/exam/**")
                        .hasRole(Role.ROLE_USER.getValue())
                        .requestMatchers("/**")
                        .hasRole(Role.ROLE_ADMIN.getValue()) // Admin users can access any endpoint
                        .anyRequest()
                        .authenticated())
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
