package com.afperdomo2.pizzaya.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizeRequests ->
                        customizeRequests
                                .requestMatchers(HttpMethod.GET, "/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                                .requestMatchers(HttpMethod.POST, "/pizzas/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/pizzas/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH).denyAll()
                                .requestMatchers("/orders/**").hasRole("ADMIN")
                                .requestMatchers("/customers/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
