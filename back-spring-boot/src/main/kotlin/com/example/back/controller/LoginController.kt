package com.example.back.controller

import com.example.back.service.LoginService
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/login")
    fun login(username: String, password: String): String {
        val isValidUser = loginService.login(username, password)

        if (!isValidUser) throw BadCredentialsException("INVALID USERNAME OR PASSWORD")

        return "SUCCESSFULLY LOGGED IN"
    }
}