package com.example.proyectoprogmovil.presentation.viewholders

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.presentation.DetallesDeEvento
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.example.proyectoprogmovil.R

class EventosCulturalesViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val tvNombreEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalNombre)
    private val tvDescripcionEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalDescripcion)
    private val tvFechaEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalFecha)
    private val tvHoraEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalHora)
    private val tvLugarEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalLugar)
    private val ivEnterArrow: ImageView = view.findViewById(R.id.ivCulturalEnterArrow)


    fun render(eventoCultural: EventoCultural, context: Context){
        tvNombreEventoCultural.text = eventoCultural.eventName
        tvDescripcionEventoCultural.text = eventoCultural.eventDescription
        tvFechaEventoCultural.text = eventoCultural.eventDate
        tvHoraEventoCultural.text = eventoCultural.eventTime
        tvLugarEventoCultural.text = eventoCultural.eventPlace

//        ivEnterArrow.setOnClickListener {
//            val intent = Intent(context, DetallesDeEvento::class.java)
//            intent.putExtra("EVENT_ID", eventoCultural.eventId)
//
//            context.startActivity(intent)
//        }

    }

}