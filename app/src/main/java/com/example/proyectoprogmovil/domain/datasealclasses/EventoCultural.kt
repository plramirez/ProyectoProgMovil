package com.example.proyectoprogmovil.domain.datasealclasses

data class EventoCultural(
    val eventId: Int = 0,
    val eventName: String = "",
    val eventDescription: String = "",
    val eventDescriptionExtense: String = "",
    val eventDate: String = "",
    val eventTime: String = "",
    val eventPlace: String = "",
    val eventImageSource: String = ""
) {
    // No-argument constructor for Firebase
    constructor() : this(0, "", "", "", "", "", "", "")
}