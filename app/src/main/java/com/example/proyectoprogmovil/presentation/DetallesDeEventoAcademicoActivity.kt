package com.example.proyectoprogmovil.presentation

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural

class DetallesDeEventoAcademicoActivity: AppCompatActivity() {

    private lateinit var btnMapaCampus: Button
    private lateinit var btnRegistrarse: Button
    private lateinit var tvDETituloEvento: TextView
    private lateinit var ivImagenEvento: ImageView
    private lateinit var tvDEDescripcionExtensaEvento: TextView
    private lateinit var tvDEFechaEvento: TextView
    private lateinit var tvDEHoraEvento: TextView
    private lateinit var tvDELugarEvento: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_de_evento_academico)

        val eventoAcademico = intent.getParcelableExtra<EventoAcademico>("EVENTO_ACADEMICO")

        initComponents()
        initListeners()
        displayAcademicEventDetails(eventoAcademico)


//        val eventosAcademicosActivity = EventosAcademicosActivity()
//        val eventoAcademico = eventosAcademicosActivity.getEventoAcademicoById(eventId)

    }

    private fun displayAcademicEventDetails(eventoAcademico: EventoAcademico?) {
        tvDETituloEvento.text = eventoAcademico?.eventName
        tvDEDescripcionExtensaEvento.text = eventoAcademico?.eventDescriptionExtense
        tvDEFechaEvento.text = eventoAcademico?.eventDate
        tvDEHoraEvento.text = eventoAcademico?.eventTime
        tvDELugarEvento.text = eventoAcademico?.eventPlace

        val imageResId = resources.getIdentifier(eventoAcademico?.eventImageSource, "drawable", packageName)
        ivImagenEvento.setImageResource(imageResId)

    }


    private fun initComponents() {
        btnMapaCampus = findViewById(R.id.btnCampusMapa)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)


        tvDETituloEvento = findViewById(R.id.tvDETituloEvento)
        ivImagenEvento = findViewById(R.id.ivImagenEvento)
        tvDEDescripcionExtensaEvento = findViewById(R.id.tvDEDescripcionExtensaEvento)
        tvDEFechaEvento = findViewById(R.id.tvDEFechaEvento)
        tvDEHoraEvento = findViewById(R.id.tvDEHoraEvento)
        tvDELugarEvento = findViewById(R.id.tvDELugarEvento)
    }

    private fun initListeners() {
        btnMapaCampus.setOnClickListener {
            val intent = Intent(this, MapaCampusActivity::class.java)
            startActivity(intent)
        }

        btnRegistrarse.setOnClickListener { showForm() }
    }

    private fun showForm() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_register)
        dialog.show()
    }
}