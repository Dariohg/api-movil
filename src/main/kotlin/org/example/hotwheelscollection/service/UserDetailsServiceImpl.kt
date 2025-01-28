package org.example.hotwheelscollection.service

import org.example.hotwheelscollection.model.User
import org.example.hotwheelscollection.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Usuario no encontrado: $username") }

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            emptyList() // Aquí puedes agregar roles si los usas
        )
    }
}
