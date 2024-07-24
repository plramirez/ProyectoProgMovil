package com.example.proyectoprogmovil

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventosAcademicosViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val tvNombreEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalNombre)
    private val tvDescripcionEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalDescripcion)
    private val tvFechaEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalFecha)
    private val tvHoraEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalHora)
    private val tvLugarEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalLugar)


    fun render(eventoAcademico: EventoAcademico){
        tvNombreEventoCultural.text = eventoAcademico.eventName
        tvDescripcionEventoCultural.text = eventoAcademico.eventDescription
        tvFechaEventoCultural.text = eventoAcademico.eventDate
        tvHoraEventoCultural.text = eventoAcademico.eventTime
        tvLugarEventoCultural.text = eventoAcademico.eventPlace
    }
}