package com.example.proyectoprogmovil.presentation

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprogmovil.R

class MapaCampusActivity : AppCompatActivity() {

    private lateinit var tvAmericanEnglishLounge : TextView
    private lateinit var tvBibliotecaLincoln : TextView
    private lateinit var tvAuditorioPatric : TextView
    private lateinit var tvMakerSpace : TextView
    private lateinit var tvPlazaEstudiantil : TextView
    private lateinit var tvRecordingStudio : TextView
    private lateinit var tvEdificioA : TextView
    private lateinit var tvEdificioE : TextView
    private lateinit var tvParqueoEdificioA : TextView
    private lateinit var tvParqueoEdificioE : TextView
    private lateinit var tvCafeteriaEdificioA : TextView
    private lateinit var tvCafeteriaEdificioE : TextView
    private lateinit var flAmericanEnglishLounge: FrameLayout
    private lateinit var flAuditorioPatrick: FrameLayout
    private lateinit var flBibliotecaLincoln: FrameLayout
    private lateinit var flCafeteriaEdificioA: FrameLayout
    private lateinit var flEdificioA: FrameLayout
    private lateinit var flEdificioE: FrameLayout
    private lateinit var flMarkerSpace: FrameLayout
    private lateinit var flParqueoEdificioE: FrameLayout
    private lateinit var flPlazaEstudiantil: FrameLayout
    private lateinit var flRecordingStudio: FrameLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_campus)

        initComponents()
        initListeners()
    }

    private fun initListeners() {
        tvAmericanEnglishLounge.setOnClickListener {
            flAmericanEnglishLounge.visibility = View.VISIBLE
        }

        flAmericanEnglishLounge.setOnClickListener {
            flAmericanEnglishLounge.visibility = View.GONE
        }

        tvAuditorioPatric.setOnClickListener {
            flAuditorioPatrick.visibility = View.VISIBLE
        }

        flAuditorioPatrick.setOnClickListener {
            flAuditorioPatrick.visibility = View.GONE
        }

        tvBibliotecaLincoln.setOnClickListener {
            flBibliotecaLincoln.visibility = View.VISIBLE
        }

        flBibliotecaLincoln.setOnClickListener {
            flBibliotecaLincoln.visibility = View.GONE
        }

        tvCafeteriaEdificioA.setOnClickListener {
            flCafeteriaEdificioA.visibility = View.VISIBLE
        }

        tvCafeteriaEdificioE.setOnClickListener {
            flCafeteriaEdificioA.visibility = View.VISIBLE
        }

        flCafeteriaEdificioA.setOnClickListener {
            flCafeteriaEdificioA.visibility = View.GONE
        }

        tvEdificioA.setOnClickListener {
            flEdificioA.visibility = View.VISIBLE
        }

        flEdificioA.setOnClickListener {
            flEdificioA.visibility = View.GONE
        }

        tvEdificioE.setOnClickListener {
            flEdificioE.visibility = View.VISIBLE
        }

        flEdificioE.setOnClickListener {
            flEdificioE.visibility = View.GONE
        }

        tvMakerSpace.setOnClickListener {
            flMarkerSpace.visibility = View.VISIBLE
        }

        flMarkerSpace.setOnClickListener {
            flMarkerSpace.visibility = View.GONE
        }

        tvParqueoEdificioE.setOnClickListener {
            flParqueoEdificioE.visibility = View.VISIBLE
        }

        tvParqueoEdificioA.setOnClickListener {
            flParqueoEdificioE.visibility = View.VISIBLE
        }

        flParqueoEdificioE.setOnClickListener {
            flParqueoEdificioE.visibility = View.GONE
        }

        tvPlazaEstudiantil.setOnClickListener {
            flPlazaEstudiantil.visibility = View.VISIBLE
        }

        flPlazaEstudiantil.setOnClickListener {
            flPlazaEstudiantil.visibility = View.GONE
        }

        tvRecordingStudio.setOnClickListener {
            flRecordingStudio.visibility = View.VISIBLE
        }

        flRecordingStudio.setOnClickListener {
            flRecordingStudio.visibility = View.GONE
        }

    }

    private fun initComponents() {
        tvAmericanEnglishLounge = findViewById(R.id.tvAmericanEnglishLounge)
        tvBibliotecaLincoln = findViewById(R.id.tvBibliotecaLincoln)
        tvAuditorioPatric = findViewById(R.id.tvAuditorio)
        tvMakerSpace = findViewById(R.id.tvMakerSpace)
        tvPlazaEstudiantil = findViewById(R.id.tvPlazaEstudiantil)
        tvRecordingStudio = findViewById(R.id.tvRecordingStudio)
        tvEdificioA = findViewById(R.id.tvEdificioA)
        tvEdificioE = findViewById(R.id.tvEdificioE)
        tvParqueoEdificioA = findViewById(R.id.tvParqueoEdificioA)
        tvParqueoEdificioE = findViewById(R.id.tvParqueoEdificioE)
        tvCafeteriaEdificioA = findViewById(R.id.tvCafeteriaEdificioA)
        tvCafeteriaEdificioE = findViewById(R.id.tvCafeteriaEdificioE)
        flAmericanEnglishLounge = findViewById(R.id.flAmericanEnglishLounge)
        flAuditorioPatrick = findViewById(R.id.flAuditorioPatrick)
        flBibliotecaLincoln = findViewById(R.id.flBibliotecaLincoln)
        flCafeteriaEdificioA = findViewById(R.id.flCafeteriaEdificioA)
        flEdificioA = findViewById(R.id.flEdificioA)
        flEdificioE = findViewById(R.id.flEdificioE)
        flMarkerSpace = findViewById(R.id.flMarkerSpace)
        flParqueoEdificioE = findViewById(R.id.flParqueoEdificioE)
        flPlazaEstudiantil = findViewById(R.id.flPlazaEstudiantil)
        flRecordingStudio = findViewById(R.id.flRecordingStudio)
    }
}