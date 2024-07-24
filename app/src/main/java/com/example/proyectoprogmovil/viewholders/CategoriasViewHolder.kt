package com.example.proyectoprogmovil.viewholders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.datasealclasses.EventoCategoria
import com.example.proyectoprogmovil.R

class CategoriasViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val tvNombreCategoria: TextView = view.findViewById(R.id.tvCategoriaNombre)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(eventoCategoria: EventoCategoria, onItemSelected: (Int) -> Unit){
        val color = if (eventoCategoria.isSelected){
            R.color.rose
        }else{
            R.color.rose_2
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when(eventoCategoria){
            EventoCategoria.Conferencias -> {
                tvNombreCategoria.text = "Conferencias"
            }

            EventoCategoria.Extracurriculares -> {
                tvNombreCategoria.text = "Extracurriculares"
            }
            EventoCategoria.Talleres -> {
                tvNombreCategoria.text = "Talleres"
            }
        }
    }
}