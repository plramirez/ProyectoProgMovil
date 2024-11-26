package com.example.proyectoprogmovil.data

import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.google.firebase.firestore.FirebaseFirestore

class EventosCulturalesRepository(private val db: FirebaseFirestore) {

    fun addEvent(event: EventoCultural, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-culturales")
            .add(event)
            .addOnSuccessListener { documentReference ->
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun updateEvent(event: EventoCultural, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-culturales")
            .document(event.documentId)
            .set(event)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun deleteEvent(eventId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-culturales")
            .document(eventId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun fetchEvents(onSuccess: (List<EventoCultural>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-culturales")
            .get()
            .addOnSuccessListener { result ->
                val events = result.map { document ->
                    document.toObject(EventoCultural::class.java).apply { documentId = document.id }
                }
                onSuccess(events)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
}