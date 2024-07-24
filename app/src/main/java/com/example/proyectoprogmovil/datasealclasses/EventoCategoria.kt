package com.example.proyectoprogmovil.datasealclasses

sealed class EventoCategoria(var isSelected: Boolean = true) {
    object Conferencias: EventoCategoria()
    object Talleres: EventoCategoria()
    object Extracurriculares: EventoCategoria()
}