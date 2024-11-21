package com.example.proyectoprogmovil.domain

import com.example.proyectoprogmovil.data.EventosAcademicosRepository
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico

class FetchEventsUseCase(private val repository: EventosAcademicosRepository) {
    fun execute(onSuccess: (List<EventoAcademico>) -> Unit, onFailure: (Exception) -> Unit) {
        repository.fetchEvents(onSuccess, onFailure)
    }
}