package com.example.proyectoprogmovil.presentation

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural

class DetallesDeEvento : AppCompatActivity() {

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
        setContentView(R.layout.activity_detalles_de_evento)

        val eventoCultural = intent.getParcelableExtra<EventoCultural>("EVENTO_CULTURAL")

        initComponents()
        initListeners()
        displayEventDetails(eventoCultural)
    }

    private fun displayEventDetails(eventoCultural: EventoCultural?) {
        tvDETituloEvento.text = eventoCultural?.eventName
        tvDEDescripcionExtensaEvento.text = eventoCultural?.eventDescriptionExtense
        tvDEFechaEvento.text = eventoCultural?.eventDate
        tvDEHoraEvento.text = eventoCultural?.eventTime
        tvDELugarEvento.text = eventoCultural?.eventPlace

        val imageResId = resources.getIdentifier(eventoCultural?.eventImageSource, "drawable", packageName)
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