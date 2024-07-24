package com.example.proyectoprogmovil

data class EventoAcademico(
    val eventName: String,
    val eventDescription: String,
    val eventPlace: String,
    val eventDate: String,
    val eventTime: String,
    val categoria: EventoCategoria,
    var isSelected: Boolean = false)