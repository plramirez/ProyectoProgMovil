package com.example.proyectoprogmovil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.adapters.CategoriasAdapter
import com.example.proyectoprogmovil.adapters.EventosAcademicosAdapter
import com.example.proyectoprogmovil.datasealclasses.EventoAcademico
import com.example.proyectoprogmovil.datasealclasses.EventoCategoria

class EventosAcademicosActivity : AppCompatActivity() {

    private val categorias = listOf(
        EventoCategoria.Conferencias,
        EventoCategoria.Talleres,
        EventoCategoria.Extracurriculares
    )

    private val eventosAcademicos = mutableListOf(
        EventoAcademico(
            "Concierto de Rock",
            "Conmemoramos la Independencia de los Estados Unidos con un épico concierto de rock en nuestra sede principal de la Av. Abraham Lincoln.",
            "Plaza del Estudiante",
            "03/07/2024 ",
            "6:00PM",
            EventoCategoria.Talleres
        ),
        EventoAcademico(
            "Celebrar la Dominicanidad",
            "¡Celebra la dominicanidad con nosotros! La Dirección Cultural del Instituto Cultural Domínico Americano te invita a formar parte de esta actividad en el Mes de la Independencia de la República Dominicana, con el apoyo de la Dirección General de Bellas Artes.",
            "Auditorio Patrick N. Hughson",
            "24/07/2024 ",
            "8:00AM",
            EventoCategoria.Conferencias
        ),
        EventoAcademico(
            "Navidad y Teatro",
            "El Instituto Cultural Domínico Americano, desde su Dirección Cultural, les invita a una celebración especial de cierre de año.",
            "Auditorio Patrick N. Hughson",
            "24/07/2024 ",
            "5:00PM",
            EventoCategoria.Extracurriculares
        )
    )

    private lateinit var rvCategorias: RecyclerView
    private lateinit var categoriasAdapter: CategoriasAdapter

    private lateinit var rvEventosAcademicos: RecyclerView
    private lateinit var eventosAcademicosAdapter: EventosAcademicosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eventos_academicos)

        initComponents()
        initUI()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents() {
        rvCategorias = findViewById(R.id.rvCategorias)
        rvEventosAcademicos = findViewById(R.id.rvEventosAcademicos)
    }

    private fun initUI() {
        categoriasAdapter = CategoriasAdapter(categorias) { position -> updateCategories(position) }
        rvCategorias.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategorias.adapter = categoriasAdapter

        eventosAcademicosAdapter = EventosAcademicosAdapter(eventosAcademicos)
        rvEventosAcademicos.layoutManager = LinearLayoutManager(this)
        rvEventosAcademicos.adapter = eventosAcademicosAdapter
    }

    private fun updateCategories(position: Int) {
        categorias[position].isSelected = !categorias[position].isSelected
        categoriasAdapter.notifyItemChanged(position)
        updateEventosAcademicos()
    }

    private fun updateEventosAcademicos() {
        val selectedCategories: List<EventoCategoria> = categorias.filter { it.isSelected }
        val nuevoEventoAcademico =
            eventosAcademicos.filter { selectedCategories.contains(it.categoria) }
        eventosAcademicosAdapter.eventosAcademicos = nuevoEventoAcademico
        eventosAcademicosAdapter.notifyDataSetChanged()
    }
}