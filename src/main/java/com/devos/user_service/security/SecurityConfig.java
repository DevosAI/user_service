package com.devos.user_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        private UserDetailService userDetailService;

        @Autowired
        public SecurityConfig(UserDetailService userDetailService) {
                this.userDetailService = userDetailService;
        }

        @Autowired
        DataSource dataSource;

        @Autowired
        AuthEntryPointJwt unauthorizedHandler;

        @Bean
        public AuthTokenFilter authicationJwtTokenFilter() {
                return new AuthTokenFilter();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(authorizeRequests->authorizeRequests
                    .requestMatchers("api/auth/login")
                    .permitAll()
                    .requestMatchers("api/user/*").permitAll()
                    .anyRequest()
                    .authenticated());
            http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.exceptionHandling(exception->exception.authenticationEntryPoint(unauthorizedHandler));
            http.headers(headers->headers.frameOptions(frameOptions->frameOptions.sameOrigin()));
            http.csrf(csrf->csrf.disable());
            http.addFilterBefore(authicationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
                return builder.getAuthenticationManager();
        }







}
