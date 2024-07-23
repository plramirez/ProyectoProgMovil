package com.example.proyectoprogmovil

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventosCulturalesViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val tvNombreEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalNombre)
    private val tvDescripcionEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalDescripcion)
    private val tvFechaEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalFecha)
    private val tvHoraEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalHora)
    private val tvLugarEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalLugar)


    fun render(eventoCultural: EventoCultural){
        tvNombreEventoCultural.text = eventoCultural.eventName
        tvDescripcionEventoCultural.text = eventoCultural.eventDescription
        tvFechaEventoCultural.text = eventoCultural.eventDate
        tvHoraEventoCultural.text = eventoCultural.eventTime
        tvLugarEventoCultural.text = eventoCultural.eventPlace
    }
}