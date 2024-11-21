package com.example.proyectoprogmovil.domain

import com.example.proyectoprogmovil.data.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {

    fun execute(email: String, password: String, name: String, phone: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        userRepository.registerUser(email, password, name, phone, onSuccess, onFailure)
    }
}