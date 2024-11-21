package com.example.proyectoprogmovil.domain.datasealclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventoAcademico(
    val eventDescriptionExtense: String,
    val eventImageSource: String,
    val eventName: String,
    val eventDescription: String,
    val eventPlace: String,
    val eventDate: String,
    val eventTime: String
): Parcelable {
    // No-argument constructor for Firebase
    constructor() : this( "", "", "", "", "", "", "")
}