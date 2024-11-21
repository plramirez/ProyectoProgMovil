package com.example.proyectoprogmovil.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.data.UserRepository
import com.example.proyectoprogmovil.domain.RegisterUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerUseCase: RegisterUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val userRepository = UserRepository(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance())
        registerUseCase = RegisterUseCase(userRepository)

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
        registerUseCase.execute(email, password, name, phone, {
            Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, { exception ->
            Toast.makeText(this, "Favor llenar todos los campos.", Toast.LENGTH_LONG).show()
        })
    }
}