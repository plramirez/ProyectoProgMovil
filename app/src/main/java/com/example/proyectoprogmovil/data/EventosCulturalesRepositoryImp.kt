package com.example.proyectoprogmovil.data


import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class EventosCulturalesRepositoryImp {

    private val db = FirebaseFirestore.getInstance()

    fun fetchEventosCulturales(
        onSuccess: (List<EventoCultural>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("eventos-culturales")
            .get()
            .addOnSuccessListener { result: QuerySnapshot ->
                val eventos = result.map { document -> document.toObject(EventoCultural::class.java) }
                onSuccess(eventos)
            }
            .addOnFailureListener { exception: java.lang.Exception ->
                onFailure(exception)
            }
    }
}