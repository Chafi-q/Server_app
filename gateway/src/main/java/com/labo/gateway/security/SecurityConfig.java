package com.labo.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/eureka/**").permitAll()
                        .pathMatchers("/public/**").permitAll()
//                        .pathMatchers("/api/users/**").permitAll()
                        .pathMatchers("/api/users/login").permitAll()
                        .pathMatchers("/api/users/send-verification-email").permitAll()
                        .pathMatchers("/api/users/**")
                        .hasRole("ADMIN")
                        .pathMatchers("/api/v1/laboratoires/**").hasAnyRole("ADMIN", "EMPLOYE")
                        .pathMatchers("/api/v1/patients/**").hasAnyRole("ADMIN", "EMPLOYE")
                        .pathMatchers("/api/v1/analyses/**").hasAnyRole("ADMIN", "EMPLOYE")
                        .pathMatchers(HttpMethod.GET, "/api/v1/analyses//{id}").hasAnyRole("USER")
                        .pathMatchers("/api/v1/examens/**").hasAnyRole("ADMIN", "EMPLOYE")
                        .pathMatchers("/api/v1/tests/**").hasAnyRole("ADMIN", "EMPLOYE")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }
}
