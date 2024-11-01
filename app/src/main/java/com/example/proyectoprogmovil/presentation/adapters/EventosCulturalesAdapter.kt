package com.example.proyectoprogmovil.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.example.proyectoprogmovil.presentation.viewholders.EventosCulturalesViewHolder
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.databinding.ItemEventoCulturalBinding

class EventosCulturalesAdapter(
    private val context: Context,
    var eventosCulturales: List<EventoCultural>
) : RecyclerView.Adapter<EventosCulturalesAdapter.EventoCulturalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoCulturalViewHolder {
        val binding = ItemEventoCulturalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoCulturalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoCulturalViewHolder, position: Int) {
        holder.bind(eventosCulturales[position], context)
    }

    override fun getItemCount(): Int = eventosCulturales.size

    class EventoCulturalViewHolder(private val binding: ItemEventoCulturalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventoCultural: EventoCultural, context: Context) {
            binding.eventoCultural = eventoCultural
            binding.executePendingBindings()
        }
    }
}