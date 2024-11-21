package com.example.proyectoprogmovil.domain

import com.example.proyectoprogmovil.data.EventosAcademicosRepository

class DeleteEventUseCase(private val repository: EventosAcademicosRepository) {
    fun execute(eventId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        repository.deleteEvent(eventId, onSuccess, onFailure)
    }
}