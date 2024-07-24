package com.example.proyectoprogmovil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EventosAcademicosAdapter(var eventosAcademicos: List<EventoAcademico>): RecyclerView.Adapter<EventosAcademicosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosAcademicosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_preview, parent, false)
        return EventosAcademicosViewHolder(view)
    }

    override fun getItemCount() = eventosAcademicos.size

    override fun onBindViewHolder(holder: EventosAcademicosViewHolder, position: Int) {
        holder.render(eventosAcademicos[position])
    }
}