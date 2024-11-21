package com.example.proyectoprogmovil.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.presentation.viewholders.EventosAcademicosViewHolder
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.databinding.ItemEventoAcademicoBinding
import com.example.proyectoprogmovil.presentation.DetallesDeEventoAcademicoActivity

class EventosAcademicosAdapter(
    private val context: Context,
    var eventosAcademicos: List<EventoAcademico>,
    private val userRole: String?,
    private val onEditButtonClick: (EventoAcademico) -> Unit,
    private val onDeleteButtonClick: (EventoAcademico) -> Unit
) : RecyclerView.Adapter<EventosAcademicosAdapter.EventoAcademicoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoAcademicoViewHolder {
        val binding = ItemEventoAcademicoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoAcademicoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoAcademicoViewHolder, position: Int) {
        holder.bind(eventosAcademicos[position], context, userRole, onEditButtonClick, onDeleteButtonClick)
    }

    override fun getItemCount(): Int = eventosAcademicos.size

    class EventoAcademicoViewHolder(private val binding: ItemEventoAcademicoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventoAcademico: EventoAcademico, context: Context, userRole: String?, onEditButtonClick: (EventoAcademico) -> Unit, onDeleteButtonClick: (EventoAcademico) -> Unit) {
            binding.eventoAcademico = eventoAcademico
            binding.executePendingBindings()

            binding.ivAcademicoEnterArrow.setOnClickListener {
                val intent = Intent(context, DetallesDeEventoAcademicoActivity::class.java)
                intent.putExtra("EVENTO_ACADEMICO", eventoAcademico)
                context.startActivity(intent)
            }

            if (userRole == "admin") {
                binding.editButton.visibility = View.VISIBLE
                binding.deleteButton.visibility = View.VISIBLE

                binding.editButton.setOnClickListener {
                    onEditButtonClick(eventoAcademico)
                }

                binding.deleteButton.setOnClickListener {
                    onDeleteButtonClick(eventoAcademico)
                }
            } else {
                binding.editButton.visibility = View.GONE
                binding.deleteButton.visibility = View.GONE
            }
        }
    }
}