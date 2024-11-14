package com.example.proyectoprogmovil.data

import com.example.proyectoprogmovil.domain.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class EventosAcademicosRepositoryImp {

    private val db = FirebaseFirestore.getInstance()

    fun fetchEventosAcademicos(
        onSuccess: (List<EventoAcademico>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("eventos-academicos")
            .get()
            .addOnSuccessListener { result: QuerySnapshot ->
                val eventos = result.map { document -> document.toObject(EventoAcademico::class.java) }
                onSuccess(eventos)
            }
            .addOnFailureListener { exception: java.lang.Exception ->
                onFailure(exception)
            }
    }
}