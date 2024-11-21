package com.example.proyectoprogmovil.domain

import com.example.proyectoprogmovil.data.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    fun execute(email: String, password: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        authRepository.loginUser(email, password, onSuccess, onFailure)
    }
}