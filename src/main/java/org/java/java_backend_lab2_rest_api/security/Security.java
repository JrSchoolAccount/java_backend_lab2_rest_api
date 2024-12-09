package org.java.java_backend_lab2_rest_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(GET, "/api/categories/**").permitAll()
                                .requestMatchers(POST, "/api/categories/**").hasAuthority("SCOPE_admin")
                                .requestMatchers(GET,"/api/locations/**").permitAll()
                                .requestMatchers(GET, "/api/locations/my-locations").authenticated()
                                .requestMatchers(POST,"/api/locations/**").authenticated()
                                .requestMatchers(
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html").permitAll()
                                .anyRequest().denyAll())
                //.httpBasic(Customizer.withDefaults());
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
/*
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .authorities("SCOPE_admin", "SCOPE_user")
                .build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .authorities("SCOPE_user")
                .build());
        return manager;
    }
 */
}
