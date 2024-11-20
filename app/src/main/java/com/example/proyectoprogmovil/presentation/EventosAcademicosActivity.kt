package com.example.proyectoprogmovil.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
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

    private lateinit var addButton: Button
    private var userRole: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_academicos)

        addButton = findViewById(R.id.addButton)

        userRole = intent.getStringExtra("userRole")

        if (userRole == "admin") {
            addButton.visibility = View.VISIBLE
            addButton.setOnClickListener {
                // Code to add a new event
            }
        } else {
            addButton.visibility = View.GONE
        }

        initComponents()
        initUI()
        fetchEventosAcademicos()
    }

    private fun initComponents() {
        rvEventosAcademicos = findViewById(R.id.rvEventosAcademicos)
    }

    private fun initUI() {
        eventosAcademicosAdapter = EventosAcademicosAdapter(this, listOf(), userRole)
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