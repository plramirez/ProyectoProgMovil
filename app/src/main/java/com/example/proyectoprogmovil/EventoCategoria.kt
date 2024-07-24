package com.example.proyectoprogmovil

sealed class EventoCategoria(var isSelected: Boolean = true) {
    object Conferencias: EventoCategoria()
    object Talleres: EventoCategoria()
    object Extracurriculares: EventoCategoria()
}