package com.example.proyectoprogmovil.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            registerUser(email, password, name, phone)
        }
    }

    private fun registerUser(email: String, password: String, name: String, phone: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        addUserInformation(user.uid, "user", name, phone) // Asignar rol de usuario por defecto
                        Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Favor llenar todos los campos.", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun addUserInformation(userId: String, role: String, name: String, phone: String) {
        val user = hashMapOf(
            "role" to role,
            "name" to name,
            "phone" to phone
        )
        db.collection("users").document(userId).set(user)
    }
}