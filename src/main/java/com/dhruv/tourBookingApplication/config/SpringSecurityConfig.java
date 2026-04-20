package com.dhruv.tourBookingApplication.config;

import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

     private final UserDetailsService userDetailsService;
     private final JwtFilter jwtFilter;

     @Autowired
    public SpringSecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
         this.jwtFilter = jwtFilter;
     }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable());
        http.sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests(auth-> auth.
                 requestMatchers(
                         "/swagger-ui/**",
                         "/swagger-ui.html",
                         "/v3/api-docs/**",
                         "/swagger-resources/**",
                         "/webjars/**"
                 ).permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/webhook/**").permitAll()
                // Public - view tours
                .requestMatchers(HttpMethod.GET, "/api/tours/**")
                .permitAll()
                // Admin only
                .requestMatchers("/api/admin/**")
                .hasAuthority("ROLE_ADMIN")

                // Customer only
                .requestMatchers("/api/bookings/**")
                .hasAuthority("ROLE_CUSTOMER")
                .anyRequest().authenticated());
        return http.build();
    }

     @Bean
     public PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }

       @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
     }

       @Bean
     public AuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
         daoAuthenticationProvider.setUserDetailsService(userDetailsService);
         daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
         return daoAuthenticationProvider;
     }



}
