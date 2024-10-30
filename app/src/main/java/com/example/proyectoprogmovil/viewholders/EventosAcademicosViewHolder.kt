package com.example.proyectoprogmovil.viewholders

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.presentation.DetallesDeEventoAcademicoActivity
import com.example.proyectoprogmovil.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.R

class EventosAcademicosViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val tvNombreEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalNombre)
    private val tvDescripcionEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalDescripcion)
    private val tvFechaEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalFecha)
    private val tvHoraEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalHora)
    private val tvLugarEventoCultural: TextView = view.findViewById(R.id.tvEventoCulturalLugar)
    private val ivEnterArrow: ImageView = view.findViewById(R.id.ivEnterArrow)


    fun render(eventoAcademico: EventoAcademico, context: Context){
        tvNombreEventoCultural.text = eventoAcademico.eventName
        tvDescripcionEventoCultural.text = eventoAcademico.eventDescription
        tvFechaEventoCultural.text = eventoAcademico.eventDate
        tvHoraEventoCultural.text = eventoAcademico.eventTime
        tvLugarEventoCultural.text = eventoAcademico.eventPlace

        ivEnterArrow.setOnClickListener {
            val intent = Intent(context, DetallesDeEventoAcademicoActivity::class.java)
            intent.putExtra("EVENT_ID", eventoAcademico.eventId)
            Log.i("EventosAcademicosViewHolder", "Click en arrow")

            context.startActivity(intent)
        }
    }
}