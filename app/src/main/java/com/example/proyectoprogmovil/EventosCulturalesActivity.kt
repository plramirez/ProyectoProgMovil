package com.example.proyectoprogmovil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.adapters.EventosCulturalesAdapter
import com.example.proyectoprogmovil.datasealclasses.EventoCultural

class EventosCulturalesActivity : AppCompatActivity() {

    private val eventosCulturales = mutableListOf(
        EventoCultural("Concierto de Rock", "Conmemoramos la Independencia de los Estados Unidos con un épico concierto de rock en nuestra sede principal de la Av. Abraham Lincoln.", "Plaza del Estudiante", "03/07/2024 ", "6:00PM"),
        EventoCultural("Celebrar la Dominicanidad", "¡Celebra la dominicanidad con nosotros! La Dirección Cultural del Instituto Cultural Domínico Americano te invita a formar parte de esta actividad en el Mes de la Independencia de la República Dominicana, con el apoyo de la Dirección General de Bellas Artes.", "Auditorio Patrick N. Hughson", "24/07/2024 ", "8:00AM"),
        EventoCultural("Navidad y Teatro", "El Instituto Cultural Domínico Americano, desde su Dirección Cultural, les invita a una celebración especial de cierre de año.", "Auditorio Patrick N. Hughson", "24/07/2024 ", "5:00PM")
    )

    private lateinit var rvEventosCulturales: RecyclerView
    private lateinit var eventosCulturalesAdapter: EventosCulturalesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_eventos_culturales)

        initComponents()
        initUI()
    }

    private fun initComponents() {
        rvEventosCulturales =findViewById(R.id.rvEventosCulturales)
    }

    private fun initUI() {
        eventosCulturalesAdapter = EventosCulturalesAdapter(this, eventosCulturales)
        rvEventosCulturales.layoutManager = LinearLayoutManager(this)
        rvEventosCulturales.adapter = eventosCulturalesAdapter

    }
}