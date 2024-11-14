package com.example.proyectoprogmovil.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.data.EventosAcademicosRepositoryImp
import com.example.proyectoprogmovil.presentation.adapters.EventosAcademicosAdapter

class EventosAcademicosActivity : AppCompatActivity() {

    private lateinit var rvEventosAcademicos: RecyclerView
    private lateinit var eventosAcademicosAdapter: EventosAcademicosAdapter

    private val repository = EventosAcademicosRepositoryImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_academicos)

        initComponents()
        initUI()
        fetchEventosAcademicos()
    }

    private fun initComponents() {
        rvEventosAcademicos = findViewById(R.id.rvEventosAcademicos)
    }

    private fun initUI() {
        eventosAcademicosAdapter = EventosAcademicosAdapter(this, listOf())
        rvEventosAcademicos.layoutManager = LinearLayoutManager(this)
        rvEventosAcademicos.adapter = eventosAcademicosAdapter
    }

    private fun fetchEventosAcademicos() {
        repository.fetchEventosAcademicos(
            onSuccess = { eventos ->
                eventosAcademicosAdapter.eventosAcademicos = eventos
                eventosAcademicosAdapter.notifyDataSetChanged()

            },
            onFailure = { exception ->
            }
        )
    }
}