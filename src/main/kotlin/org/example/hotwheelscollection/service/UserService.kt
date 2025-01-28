package org.example.hotwheelscollection.service

import org.example.hotwheelscollection.model.User
import org.example.hotwheelscollection.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun registerUser(username: String, password: String, email: String): User {
        val encodedPassword = passwordEncoder.encode(password)
        val newUser = User(username = username, password = encodedPassword, email = email)
        return userRepository.save(newUser)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username).orElse(null)
    }
}
