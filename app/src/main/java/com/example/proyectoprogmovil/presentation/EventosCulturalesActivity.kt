package com.example.proyectoprogmovil.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.presentation.adapters.EventosCulturalesAdapter
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.google.firebase.firestore.FirebaseFirestore

class EventosCulturalesActivity : AppCompatActivity() {

    private lateinit var rvEventosCulturales: RecyclerView
    private lateinit var eventosCulturalesAdapter: EventosCulturalesAdapter

    private lateinit var addButton: Button
    private var userRole: String? = null

    private val eventosList = mutableListOf<EventoCultural>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_culturales)

        addButton = findViewById(R.id.addButton)

        userRole = intent.getStringExtra("userRole")

        checkUserRole()

        initComponents()
        initUI()
        fetchEventosCulturales()
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
        rvEventosCulturales = findViewById(R.id.rvEventosCulturales)
    }

    private fun initUI() {
        eventosCulturalesAdapter = EventosCulturalesAdapter(this, listOf(), userRole, ::onEditButtonClick, ::onDeleteButtonClick)
        rvEventosCulturales.layoutManager = LinearLayoutManager(this)
        rvEventosCulturales.adapter = eventosCulturalesAdapter
    }

    //Showing the dialog to add an event
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

            val event = EventoCultural(
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

    private fun showEditEventDialog(event: EventoCultural) {
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

            updateEventInFirestore(updatedEvent)
            dialog.dismiss()
        }

        dialog.show()
    }

    //Adding the event to Firestore
    private fun addEventToFirestore(event: EventoCultural) {
        val db = FirebaseFirestore.getInstance()
        db.collection("eventos-culturales")
            .add(event)
            .addOnSuccessListener { documentReference ->
                event.documentId = documentReference.id
                eventosList.add(event)
                eventosCulturalesAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    private fun updateEventInFirestore(event: EventoCultural) {
        val db = FirebaseFirestore.getInstance()
        db.collection("eventos-culturales")
            .document(event.documentId)
            .set(event)
            .addOnSuccessListener {
                val index = eventosList.indexOfFirst { it.documentId == event.documentId }
                if (index != -1) {
                    eventosList[index] = event
                    eventosCulturalesAdapter.notifyItemChanged(index)
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    private fun deleteEventFromFirestore(event: EventoCultural) {
        val db = FirebaseFirestore.getInstance()
        db.collection("eventos-culturales")
            .document(event.documentId)
            .delete()
            .addOnSuccessListener {
                val index = eventosList.indexOfFirst { it.documentId == event.documentId }
                if (index != -1) {
                    eventosList.removeAt(index)
                    eventosCulturalesAdapter.notifyItemRemoved(index)
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    private fun onDeleteButtonClick(event: EventoCultural) {
        deleteEventFromFirestore(event)
    }

    private fun fetchEventosCulturales() {
        val db = FirebaseFirestore.getInstance()
        db.collection("eventos-culturales")
            .get()
            .addOnSuccessListener { result ->
                eventosList.clear()
                for (document in result) {
                    val event = document.toObject(EventoCultural::class.java)
                    event.documentId = document.id
                    eventosList.add(event)
                }
                eventosCulturalesAdapter.eventosCulturales = eventosList
                eventosCulturalesAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }

    private fun onEditButtonClick(event: EventoCultural) {
        showEditEventDialog(event)
    }
}