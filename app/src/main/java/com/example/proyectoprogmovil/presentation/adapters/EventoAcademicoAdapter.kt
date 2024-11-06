package com.example.proyectoprogmovil.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.presentation.viewholders.EventosAcademicosViewHolder
import com.example.proyectoprogmovil.R

class EventosAcademicosAdapter(private val context: Context, var eventosAcademicos: List<EventoAcademico>): RecyclerView.Adapter<EventosAcademicosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosAcademicosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evento_academico, parent, false)
        return EventosAcademicosViewHolder(view)
    }

    override fun getItemCount() = eventosAcademicos.size

    override fun onBindViewHolder(holder: EventosAcademicosViewHolder, position: Int) {
        holder.render(eventosAcademicos[position], context)
    }
}