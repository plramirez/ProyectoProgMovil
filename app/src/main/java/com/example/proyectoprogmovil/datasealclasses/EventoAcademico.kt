package com.example.proyectoprogmovil.datasealclasses

data class EventoAcademico(
    val eventId: Int,
    val eventDescriptionExtense: String,
    val eventImageSource: String,
    val eventName: String,
    val eventDescription: String,
    val eventPlace: String,
    val eventDate: String,
    val eventTime: String,
    val categoria: EventoCategoria,
    var isSelected: Boolean = true)