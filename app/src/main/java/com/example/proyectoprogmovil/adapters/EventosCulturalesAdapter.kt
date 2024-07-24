package com.example.proyectoprogmovil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.datasealclasses.EventoCultural
import com.example.proyectoprogmovil.viewholders.EventosCulturalesViewHolder
import com.example.proyectoprogmovil.R

class EventosCulturalesAdapter(var eventosCulturales: List<EventoCultural>): RecyclerView.Adapter<EventosCulturalesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosCulturalesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_preview, parent, false)
        return EventosCulturalesViewHolder(view)
    }

    override fun getItemCount() = eventosCulturales.size

    override fun onBindViewHolder(holder: EventosCulturalesViewHolder, position: Int) {
        holder.render(eventosCulturales[position])
    }
}