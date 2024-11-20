package com.example.proyectoprogmovil.presentation


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.presentation.adapters.EventosCulturalesAdapter
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.example.proyectoprogmovil.data.EventosCulturalesRepositoryImp

class EventosCulturalesActivity : AppCompatActivity() {

    private lateinit var rvEventosCulturales: RecyclerView
    private lateinit var eventosCulturalesAdapter: EventosCulturalesAdapter
    private val repository = EventosCulturalesRepositoryImp()

    private lateinit var addButton: Button
    private var userRole: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_culturales)

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
        fetchEventosCulturales()
    }

    private fun initComponents() {
        rvEventosCulturales = findViewById(R.id.rvEventosCulturales)
    }

    private fun initUI() {
        eventosCulturalesAdapter = EventosCulturalesAdapter(this, listOf(), userRole)
        rvEventosCulturales.layoutManager = LinearLayoutManager(this)
        rvEventosCulturales.adapter = eventosCulturalesAdapter
    }

    private fun fetchEventosCulturales() {
        repository.fetchEventosCulturales(
            onSuccess = { eventos ->
                eventosCulturalesAdapter.eventosCulturales = eventos
                eventosCulturalesAdapter.notifyDataSetChanged()
            },
            onFailure = { exception ->
                // Handle the error
            }
        )
    }

//    fun getEventoCulturalById(eventId: Int): EventoCultural? {
//        return eventosCulturalesAdapter.eventosCulturales.find { it.eventId == eventId }
//    }
}