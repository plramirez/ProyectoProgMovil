package com.example.proyectoprogmovil.domain

import com.example.proyectoprogmovil.data.EventosAcademicosRepository
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico

class UpdateEventUseCase(private val repository: EventosAcademicosRepository) {
    fun execute(event: EventoAcademico, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        repository.updateEvent(event, onSuccess, onFailure)
    }
}