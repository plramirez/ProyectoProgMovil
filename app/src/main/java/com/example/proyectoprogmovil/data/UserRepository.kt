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
                        addUserInformation(user.uid, email, "user", name, phone, onSuccess, onFailure)
                    }
                } else {
                    task.exception?.let { onFailure(it) }
                }
            }
    }

    private fun addUserInformation(userId: String, email: String, role: String, name: String, phone: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val user = hashMapOf(
            "email" to email,
            "role" to role,
            "name" to name,
            "phone" to phone
        )
        db.collection("users").document(userId).set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e) }
    }


    fun getCurrentUser(onSuccess: (User) -> Unit, onFailure: (Exception) -> Unit) {
        val user = auth.currentUser
        if (user != null) {
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    val userInfo = document.toObject(User::class.java)
                    if (userInfo != null) {
                        onSuccess(userInfo)
                    } else {
                        onFailure(Exception("User not found"))
                    }
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("No user logged in"))
        }
    }

}

data class User(
    val name: String = "",
    val email: String = "",
    val phone: String = ""
)