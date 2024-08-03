package com.example.proyectoprogmovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val btnEventosAcademicos: Button = findViewById(R.id.btnAcademicEvents)
        val btnEventosCulturales: Button = findViewById(R.id.btnCulturalEvents)
        val btnMapaCampus: Button = findViewById(R.id.btnCampusMap)

        btnEventosAcademicos.setOnClickListener { navigateToEventosAcademicos() }
        btnEventosCulturales.setOnClickListener { navigateToEventosCulturales() }
        btnMapaCampus.setOnClickListener { navigateToMapaCampus() }

    }
    private fun navigateToEventosAcademicos(){
        val intent = Intent(this, EventosAcademicosActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToEventosCulturales(){
        val intent = Intent(this, EventosCulturalesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMapaCampus(){
        val intent = Intent(this, MapaCampusActivity::class.java)
        startActivity(intent)
    }
}