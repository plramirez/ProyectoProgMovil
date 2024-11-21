package com.example.proyectoprogmovil.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.app.Dialog
import android.view.LayoutInflater
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.data.EventosAcademicosRepositoryImp
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.presentation.adapters.EventosAcademicosAdapter
import com.google.firebase.firestore.FirebaseFirestore

class EventosAcademicosActivity : AppCompatActivity() {

    private lateinit var rvEventosAcademicos: RecyclerView
    private lateinit var eventosAcademicosAdapter: EventosAcademicosAdapter
    private val repository = EventosAcademicosRepositoryImp()

    private lateinit var addButton: Button
    private var userRole: String? = null


    private val eventosList = mutableListOf<EventoAcademico>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_academicos)

        addButton = findViewById(R.id.addButton)

        userRole = intent.getStringExtra("userRole")

        if (userRole == "admin") {
            addButton.visibility = View.VISIBLE
            addButton.setOnClickListener {
                showAddEventDialog()
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

    //Adding an event
    private fun showAddEventDialog() {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_add_event, null)
        dialog.setContentView(view)

        val etEventName = view.findViewById<EditText>(R.id.etEventName)
        val etEventDescription = view.findViewById<EditText>(R.id.etEventDescription)
        val etEventDescriptionExtense = view.findViewById<EditText>(R.id.etEventDescriptionExtense)
        val etEventPlace = view.findViewById<EditText>(R.id.etEventPlace)
        val etEventDate = view.findViewById<EditText>(R.id.etEventDate)
        val etEventTime = view.findViewById<EditText>(R.id.etEventTime)
        val btnAddEvent = view.findViewById<Button>(R.id.btnConfirmation)

        btnAddEvent.setOnClickListener {
            val eventName = etEventName.text.toString().trim()
            val eventDescription = etEventDescription.text.toString().trim()
            val eventDescriptionExtense = etEventDescriptionExtense.text.toString().trim()
            val eventPlace = etEventPlace.text.toString().trim()
            val eventDate = etEventDate.text.toString().trim()
            val eventTime = etEventTime.text.toString().trim()

            if (eventName.isEmpty() || eventDescription.isEmpty() || eventDescriptionExtense.isEmpty() || eventPlace.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty()) {
                // Show error message
                return@setOnClickListener
            }

            val event = EventoAcademico(
                eventDescriptionExtense = eventDescriptionExtense,
                eventImageSource = "unicda_eventss",
                eventName = eventName,
                eventDescription = eventDescription,
                eventPlace = eventPlace,
                eventDate = eventDate,
                eventTime = eventTime
            )

            addEventToFirestore(event)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun addEventToFirestore(event: EventoAcademico) {
        val db = FirebaseFirestore.getInstance()
        db.collection("eventos-academicos")
            .add(event)
            .addOnSuccessListener {
                eventosList.add(event)
                eventosAcademicosAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
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