package com.example.proyectoprogmovil.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.R
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
            4,
            "Welcome to our flavor-filled learning session! \uD83C\uDF7D\uFE0F Let's talk about how English can open doors to a culinary world full of delights and creative solutions.\n" +
                    "\n" +
                    "Come and learn as we explore this tasty topic together!\n",
            "food_and_cooking",
            "Open Learning Program - Food and Cooking",
            "Sharing recipes, discussing favorites cuisines, and talking about culinary experiences.",
            "Virtual",
            "May 22nd ",
            "6:00PM",
            EventoCategoria.Talleres
        ),
        EventoAcademico(
            5,
            "\uD83C\uDF1F Mark Your Calendars! \uD83C\uDF1F Join us for the National Conference for Teachers of English 2024: ELT Connecting Communities, Cultures, and Classrooms! \uD83D\uDDD3\uFE0F Save the Date: August 7-9, 2024. Let's explore the transformative power of English language education.\n" +
                    "\n" +
                    "Prepare yourself for an incredible opportunity to enhance your skills and knowledge in teaching and learning English, right here in the beautiful Dominican Republic. This event promises to be an inspiring and enriching experience for all attendees.\n" +
                    "\n" +
                    "Don't forget to save the date and keep an eye on our social media channels for more exciting details and updates.\n" +
                    "\n" +
                    "We can't wait to see you there!",
            "english_teachers_conference",
            "Annual Conference for Teachers of English",
            "\uD83C\uDF1F Mark Your Calendars! \uD83C\uDF1F Join us for the National Conference for Teachers of English 2024: ELT Connecting Communities, Cultures, and Classrooms! \uD83D\uDDD3\uFE0F ",
            "Hotel Catalonia, Santo Domingo",
            "August 7th, 8th ",
            "8:00AM - 6:00PM",
            EventoCategoria.Conferencias
        ),
        EventoAcademico(
            6,
            "¡Haz del inglés tu aliado y habla con fluidez sin límites ahora con nueva herramienta IA!✨\uD83D\uDDE3\uFE0F\n\n" +
                    "No esperes más para inscribirte en nuestro programa de Conversación para el próximo ciclo julio-septiembre. Convierte tus habilidades en conversación en una ventaja competitiva. \uD83C\uDF1F\n\n",
            "programa_conversacion_virtual",
            "Programa Conversación Virtual – Con Nueva Herramienta de Inteligencia Artificial",
            "Programa Conversacion Virtual – Con Nueva Herramienta de Inteligencia Artificial",
            "Virtual",
            "Julio - Septiembre ",
            "6:00PM",
            EventoCategoria.Extracurriculares
        ),
        EventoAcademico(
            7,
            "¿Listo para conquistar el TOEFL y alcanzar tus metas académicas y profesionales en el extranjero? \uD83C\uDF0D✈\uFE0F\n\n" +
                    "Nuestro curso preparatorio en modalidad virtual te lleva paso a paso hacia el éxito en el examen TOEFL, requisito clave para universidades y empleos en Estados Unidos.\n\n" +
                    "¡Inscríbete ahora y asegura tu futuro! \uD83C\uDF93\n",
            "curso_toefl",
            "Curso Preparatorio TOEFL",
            "¿Listo para conquistar el TOEFL y alcanzar tus metas académicas y profesionales en el extranjero? \uD83C\uDF0D✈\uFE0F",
            "Virtual",
            "Julio - Septiembre ",
            "6:00PM",
            EventoCategoria.Extracurriculares
        ),
        EventoAcademico(
            8,
            "Aún estás a tiempo para descubrir el poder del aprendizaje STEAM, únete a nuestro Hackathon.\n" +
                    "\n" +
                    "¡Es la oportunidad perfecta para jóvenes apasionados por crear un impacto positivo en el mundo!\n" +
                    "\n" +
                    "Para registrarte ingresa a: www.onlinelearningcommunity.info. Tienes hasta el 14 de junio ⌛\uFE0F\n" +
                    "\n" +
                    "¡No te pierdas esta experiencia única!",
            "hackathon",
            "Hackathon",
            "¡Es la oportunidad perfecta para jóvenes apasionados por crear un impacto positivo en el mundo!",
            "MakerSpace",
            "20 y 21 de Junio 2024 ",
            "9:00AM - 6:00PM",
            EventoCategoria.Extracurriculares
        )
    )

    private lateinit var rvCategorias: RecyclerView
    private lateinit var categoriasAdapter: CategoriasAdapter

    private lateinit var rvEventosAcademicos: RecyclerView
    private lateinit var eventosAcademicosAdapter: EventosAcademicosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_academicos)

        initComponents()
        initUI()
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

        eventosAcademicosAdapter = EventosAcademicosAdapter(this, eventosAcademicos)
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

    fun getEventoAcademicoById(eventId: Int): EventoAcademico? {
        return eventosAcademicos.find { it.eventId == eventId }
    }
}