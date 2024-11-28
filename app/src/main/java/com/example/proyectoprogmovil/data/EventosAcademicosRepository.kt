package com.example.proyectoprogmovil.data

import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.google.firebase.firestore.FirebaseFirestore

class EventosAcademicosRepository(private val db: FirebaseFirestore) {

    fun addEvent(event: EventoAcademico, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-academicos")
            .add(event)
            .addOnSuccessListener { documentReference ->
                onSuccess(documentReference.id)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun updateEvent(event: EventoAcademico, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-academicos")
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
        db.collection("eventos-academicos")
            .document(eventId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun fetchEvents(onSuccess: (List<EventoAcademico>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("eventos-academicos")
            .get()
            .addOnSuccessListener { result ->
                val events = result.map { document ->
                    document.toObject(EventoAcademico::class.java).apply { documentId = document.id }
                }
                onSuccess(events)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun registerUserForEvent(eventId: String, user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val registrationsRef = db.collection("eventos-academicos").document(eventId).collection("registrations")

        registrationsRef.whereEqualTo("email", user.email).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    val registration = hashMapOf(
                        "name" to user.name,
                        "email" to user.email,
                        "phone" to user.phone
                    )
                    registrationsRef.add(registration)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { e -> onFailure(e) }
                } else {
                    onFailure(Exception("Este usuario ya está registrado en este evento."))
                }
            }
            .addOnFailureListener { e -> onFailure(e) }
    }
}