package com.example.proyectoprogmovil.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.presentation.viewholders.CategoriasViewHolder
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCategoria
import com.example.proyectoprogmovil.R

class CategoriasAdapter(
    private val categorias: List<EventoCategoria>,
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<CategoriasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event_category, parent, false)
        return CategoriasViewHolder(view)
    }

    override fun getItemCount() = categorias.size

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {
        holder.render(categorias[position], onItemSelected)
    }

}