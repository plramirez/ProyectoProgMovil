package com.example.proyectoprogmovil.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository(private val auth: FirebaseAuth, private val db: FirebaseFirestore) {

    fun registerUser(email: String, password: String, name: String, phone: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        addUserInformation(user.uid, "user", name, phone, onSuccess, onFailure)
                    }
                } else {
                    task.exception?.let { onFailure(it) }
                }
            }
    }

    private fun addUserInformation(userId: String, role: String, name: String, phone: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val user = hashMapOf(
            "role" to role,
            "name" to name,
            "phone" to phone
        )
        db.collection("users").document(userId).set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }
}