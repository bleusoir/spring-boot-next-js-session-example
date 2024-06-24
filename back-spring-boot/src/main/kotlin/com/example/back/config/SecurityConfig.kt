package com.example.back.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain = http
        .csrf { it.disable() }
        .formLogin {
            it
                .loginPage("/login")
                .loginProcessingUrl("/auth/process")
                .successForwardUrl("/?succ")
                .failureForwardUrl("/?fail")
        }
        .headers { headers ->
            headers.frameOptions {
                it.sameOrigin()
            }
        }
        .authorizeHttpRequests {
            it
                .requestMatchers("/api/**").authenticated()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().permitAll()
        }
        .sessionManagement { it.maximumSessions(1).maxSessionsPreventsLogin(false) }
        .logout { it.permitAll() }
        .build()
}