package com.example.proyectoprogmovil.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MenuPrincipal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val btnEventosAcademicos: Button = findViewById(R.id.btnAcademicEvents)
        val btnEventosCulturales: Button = findViewById(R.id.btnCulturalEvents)
        val btnMapaCampus: Button = findViewById(R.id.btnCampusMap)
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)

        btnEventosAcademicos.setOnClickListener { navigateToEventosAcademicos() }
        btnEventosCulturales.setOnClickListener { navigateToEventosCulturales() }
        btnMapaCampus.setOnClickListener { navigateToMapaCampus() }

        welcomeUser(welcomeTextView)
    }

    private fun welcomeUser(welcomeTextView: TextView) {
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val name = document.getString("name")
                        welcomeTextView.text = "¡Bienvenido, $name!"
                    }
                }
                .addOnFailureListener { exception ->
                    // Handle any errors
                }
        }
    }

    private fun navigateToEventosAcademicos() {
        val intent = Intent(this, EventosAcademicosActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToEventosCulturales() {
        val intent = Intent(this, EventosCulturalesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMapaCampus() {
        val intent = Intent(this, MapaCampusActivity::class.java)
        startActivity(intent)
    }
}