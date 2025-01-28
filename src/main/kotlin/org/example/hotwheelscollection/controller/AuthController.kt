package org.example.hotwheelscollection.controller

import org.example.hotwheelscollection.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    fun register(@RequestParam username: String, @RequestParam password: String, @RequestParam email: String): ResponseEntity<String> {
        return try {
            userService.registerUser(username, password, email)
            ResponseEntity.ok("Usuario registrado con éxito")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Error al registrar usuario: ${e.message}")
        }
    }

    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): ResponseEntity<String> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(username, password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        return ResponseEntity.ok("Inicio de sesión exitoso")
    }
}
