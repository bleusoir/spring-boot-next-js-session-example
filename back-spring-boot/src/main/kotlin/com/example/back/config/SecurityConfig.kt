package com.example.back.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain = http
        .csrf { it.disable() }
        .authorizeHttpRequests {
            it
                .requestMatchers("/", "/login")
                .permitAll()
                .anyRequest()
                .authenticated()
        }
        .logout { it.permitAll() }
//        .default
        .sessionManagement { it.maximumSessions(1).maxSessionsPreventsLogin(false) }
        .build()
}