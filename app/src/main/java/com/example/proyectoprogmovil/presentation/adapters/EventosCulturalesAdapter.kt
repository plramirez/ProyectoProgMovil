package com.example.proyectoprogmovil.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.example.proyectoprogmovil.presentation.viewholders.EventosCulturalesViewHolder
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.databinding.ItemEventoCulturalBinding
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.presentation.DetallesDeEvento

class EventosCulturalesAdapter(
    private val context: Context,
    var eventosCulturales: List<EventoCultural>,
    private val userRole: String?,
    private val onEditButtonClick: (EventoCultural) -> Unit
) : RecyclerView.Adapter<EventosCulturalesAdapter.EventoCulturalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoCulturalViewHolder {
        val binding = ItemEventoCulturalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoCulturalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoCulturalViewHolder, position: Int) {
        holder.bind(eventosCulturales[position], context, userRole, onEditButtonClick)
    }

    override fun getItemCount(): Int = eventosCulturales.size

    class EventoCulturalViewHolder(private val binding: ItemEventoCulturalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventoCultural: EventoCultural, context: Context, userRole: String?, onEditButtonClick: (EventoCultural) -> Unit) {
            binding.eventoCultural = eventoCultural
            binding.executePendingBindings()

            binding.ivCulturalEnterArrow.setOnClickListener {
                val intent = Intent(context, DetallesDeEvento::class.java)
                intent.putExtra("EVENTO_CULTURAL", eventoCultural)
                context.startActivity(intent)
            }

            if (userRole == "admin") {
                binding.editButton.visibility = View.VISIBLE
                binding.deleteButton.visibility = View.VISIBLE

                binding.editButton.setOnClickListener {
                    onEditButtonClick(eventoCultural)
                }

                binding.deleteButton.setOnClickListener {
                    // Code to delete the event
                }
            } else {
                binding.editButton.visibility = View.GONE
                binding.deleteButton.visibility = View.GONE
            }
        }
    }
}