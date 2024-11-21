package com.example.proyectoprogmovil.domain

import com.example.proyectoprogmovil.data.EventosAcademicosRepository
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico

class AddEventUseCase(private val repository: EventosAcademicosRepository) {
    fun execute(event: EventoAcademico, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        repository.addEvent(event, onSuccess, onFailure)
    }
}