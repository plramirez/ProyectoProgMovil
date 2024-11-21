package com.example.proyectoprogmovil.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.data.AuthRepository
import com.example.proyectoprogmovil.domain.LoginUseCase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginUseCase: LoginUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val authRepository = AuthRepository(FirebaseAuth.getInstance())
        loginUseCase = LoginUseCase(authRepository)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(email, password)
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        loginUseCase.execute(email, password, {
            Toast.makeText(this, "¡Acceso exitoso!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MenuPrincipal::class.java))
            finish()
        }, { exception ->
            Toast.makeText(this, "Correo o contraseña incorrectos, verificar que el correo haya sido registrado.", Toast.LENGTH_LONG).show()
        })
    }
}