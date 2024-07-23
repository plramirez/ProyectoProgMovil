package com.example.proyectoprogmovil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventosCulturalesActivity : AppCompatActivity() {


    private val eventosCulturales = mutableListOf(
        EventoCultural("Rock Mundial", "Un evento muy bacano de rock.", "En la explanada", "24/07/2024", "6:00PM"),
        EventoCultural("Rock sdasdasdasdMundial", "Un evento muy bacano sadasdasdasdde rock.", "En laasdasdasdasd explanada", "24/07/2024", "6:00PM")
    )

    private lateinit var rvEventosCulturales: RecyclerView
    private lateinit var eventosCulturalesAdapter:EventosCulturalesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eventos_culturales)

        initComponents()
        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents() {
        rvEventosCulturales =findViewById(R.id.rvEventosCulturales)
    }

    private fun initUI() {
        eventosCulturalesAdapter = EventosCulturalesAdapter(eventosCulturales)
        rvEventosCulturales.layoutManager = LinearLayoutManager(this)
        rvEventosCulturales.adapter = eventosCulturalesAdapter

    }

}