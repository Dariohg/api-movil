package org.example.hotwheelscollection.service

import org.example.hotwheelscollection.model.User
import org.example.hotwheelscollection.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username).orElse(null)
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun registerUser(username: String, password: String, email: String): User {
        val hashedPassword = passwordEncoder.encode(password)
        val newUser = User(username = username, password = hashedPassword, email = email)
        return userRepository.save(newUser)
    }
}
