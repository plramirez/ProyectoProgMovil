package com.example.proyectoprogmovil.domain.datasealclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventoCultural(
    val eventName: String = "",
    val eventDescription: String = "",
    val eventDescriptionExtense: String = "",
    val eventDate: String = "",
    val eventTime: String = "",
    val eventPlace: String = "",
    val eventImageSource: String = ""
) : Parcelable {
    // No-argument constructor for Firebase
    constructor() : this("", "", "", "", "", "", "")
}