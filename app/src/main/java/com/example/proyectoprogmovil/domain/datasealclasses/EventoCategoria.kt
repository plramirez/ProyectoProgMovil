package com.example.proyectoprogmovil.domain.datasealclasses

sealed class EventoCategoria(var isSelected: Boolean = false) {
    object Conferencias: EventoCategoria()
    object Talleres: EventoCategoria()
    object Extracurriculares: EventoCategoria()
}