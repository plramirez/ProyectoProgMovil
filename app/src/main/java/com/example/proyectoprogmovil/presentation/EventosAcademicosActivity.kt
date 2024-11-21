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
import com.example.proyectoprogmovil.data.EventosAcademicosRepository
import com.example.proyectoprogmovil.domain.*
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.presentation.adapters.EventosAcademicosAdapter
import com.google.firebase.firestore.FirebaseFirestore

class EventosAcademicosActivity : AppCompatActivity() {

    private lateinit var rvEventosAcademicos: RecyclerView
    private lateinit var eventosAcademicosAdapter: EventosAcademicosAdapter
    private lateinit var addButton: Button
    private var userRole: String? = null

    private val eventosList = mutableListOf<EventoAcademico>()

    private val repository = EventosAcademicosRepository(FirebaseFirestore.getInstance())
    private val addEventUseCase = AddEventUseCase(repository)
    private val updateEventUseCase = UpdateEventUseCase(repository)
    private val deleteEventUseCase = DeleteEventUseCase(repository)
    private val fetchEventsUseCase = FetchEventsUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_academicos)

        addButton = findViewById(R.id.addButton)
        userRole = intent.getStringExtra("userRole")

        checkUserRole()

        initComponents()
        initUI()
        fetchEventosAcademicos()
    }

    private fun checkUserRole() {
        if (userRole == "admin") {
            addButton.visibility = View.VISIBLE
            addButton.setOnClickListener {
                showAddEventDialog()
            }
        } else {
            addButton.visibility = View.GONE
        }
    }

    private fun initComponents() {
        rvEventosAcademicos = findViewById(R.id.rvEventosAcademicos)
    }

    private fun initUI() {
        eventosAcademicosAdapter = EventosAcademicosAdapter(this, listOf(), userRole, ::onEditButtonClick, ::onDeleteButtonClick)
        rvEventosAcademicos.layoutManager = LinearLayoutManager(this)
        rvEventosAcademicos.adapter = eventosAcademicosAdapter
    }

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

            addEventUseCase.execute(event, { documentId ->
                event.documentId = documentId
                eventosList.add(event)
                eventosAcademicosAdapter.notifyDataSetChanged()
            }, { e ->
                // Handle failure
            })
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showEditEventDialog(event: EventoAcademico) {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_add_event, null)
        dialog.setContentView(view)

        val etEventName = view.findViewById<EditText>(R.id.etEventName)
        val etEventDescription = view.findViewById<EditText>(R.id.etEventDescription)
        val etEventDescriptionExtense = view.findViewById<EditText>(R.id.etEventDescriptionExtense)
        val etEventPlace = view.findViewById<EditText>(R.id.etEventPlace)
        val etEventDate = view.findViewById<EditText>(R.id.etEventDate)
        val etEventTime = view.findViewById<EditText>(R.id.etEventTime)
        val btnEditEvent = view.findViewById<Button>(R.id.btnConfirmation)

        etEventName.setText(event.eventName)
        etEventDescription.setText(event.eventDescription)
        etEventDescriptionExtense.setText(event.eventDescriptionExtense)
        etEventPlace.setText(event.eventPlace)
        etEventDate.setText(event.eventDate)
        etEventTime.setText(event.eventTime)

        btnEditEvent.setOnClickListener {
            val updatedEvent = event.copy(
                eventName = etEventName.text.toString().trim(),
                eventDescription = etEventDescription.text.toString().trim(),
                eventDescriptionExtense = etEventDescriptionExtense.text.toString().trim(),
                eventPlace = etEventPlace.text.toString().trim(),
                eventDate = etEventDate.text.toString().trim(),
                eventTime = etEventTime.text.toString().trim()
            )

            updateEventUseCase.execute(updatedEvent, {
                val index = eventosList.indexOfFirst { it.documentId == updatedEvent.documentId }
                if (index != -1) {
                    eventosList[index] = updatedEvent
                    eventosAcademicosAdapter.notifyItemChanged(index)
                }
            }, { e ->
                // Handle failure
            })
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun deleteEventFromFirestore(event: EventoAcademico) {
        deleteEventUseCase.execute(event.documentId, {
            val index = eventosList.indexOfFirst { it.documentId == event.documentId }
            if (index != -1) {
                eventosList.removeAt(index)
                eventosAcademicosAdapter.notifyItemRemoved(index)
            }
        }, { e ->
            // Handle failure
        })
    }

    private fun onDeleteButtonClick(event: EventoAcademico) {
        deleteEventFromFirestore(event)
    }

    private fun fetchEventosAcademicos() {
        fetchEventsUseCase.execute({ events ->
            eventosList.clear()
            eventosList.addAll(events)
            eventosAcademicosAdapter.eventosAcademicos = eventosList
            eventosAcademicosAdapter.notifyDataSetChanged()
        }, { e ->
            // Handle failure
        })
    }

    private fun onEditButtonClick(event: EventoAcademico) {
        showEditEventDialog(event)
    }
}