package com.example.proyectoprogmovil

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallesDeEvento : AppCompatActivity() {

    private lateinit var btnMapaCampus : Button
    private lateinit var btnRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_de_evento)

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnMapaCampus = findViewById(R.id.btnCampusMapa)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
    }

    private fun initListeners() {
        btnMapaCampus.setOnClickListener {
            val intent = Intent(this, MapaCampusActivity::class.java)
            startActivity(intent)
        }

        btnRegistrarse.setOnClickListener { showForm() }
    }

    private fun showForm() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_register)

        dialog.show()
    }
}