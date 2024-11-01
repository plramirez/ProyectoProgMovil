package com.example.proyectoprogmovil.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoprogmovil.R
import com.example.proyectoprogmovil.presentation.adapters.EventosCulturalesAdapter
import com.example.proyectoprogmovil.domain.datasealclasses.EventoCultural
import com.google.firebase.firestore.FirebaseFirestore

class EventosCulturalesActivity : AppCompatActivity() {

    private val eventosCulturales = mutableListOf(
        EventoCultural(
            1,
            "Celebremos juntos la independencia de los Estados Unidos con un increíble concierto rock! Este 3 de julio a las 6:00pm, no te puedes perder la energía de @neokarmarockband en la sede principal del Instituto Cultural Dominico Americano.\n\n" +
                    "Disfruta de música en vivo, deliciosas propuestas gastronómicas y entrada libre para todo. ¡Te esperamos en Av. Abraham Lincoln #21 para una noche inolvidable!\n\n" +
                    "\uD83C\uDFA4 Banda: Neokarma Rock Band\n\n" +
                    "Además, tendremos un DJ en vivo, propuesta gastronómica estadounidense y un Hot Dog Eating Contest! \uD83C\uDF2D\uD83C\uDFB6\n\n" +
                    "\uD83C\uDFB8 ¡Vive la energía, la pasión y la magia del rock en cada acorde! \uD83C\uDFB8 #4deJulio #RockAndRoll #ConciertoInolvidable #Celebración #DomínicoAmericano \uD83C\uDDFA\uD83C\uDDF8\uD83C\uDFB8\n\n",
            "ie_concierto_rock",
            "Concierto de Rock",
            "Conmemoramos la Independencia de los Estados Unidos con un épico concierto de rock en nuestra sede principal de la Av. Abraham Lincoln.",
            "Plaza del Estudiante",
            "03/07/2024 ",
            "6:00PM"
        ),
        EventoCultural(
            2,
            "¡Celebra la dominicanidad con nosotros! \uD83C\uDDE9\uD83C\uDDF4 La Dirección Cultural del Instituto Cultural Domínico Americano te invita a formar parte de esta actividad en el Mes de la Independencia de la República Dominicana, con el apoyo de la Dirección General de Bellas Artes.\n\n" +
                    "\uD83C\uDFB6 Disfruta de las magníficas interpretaciones de la Coral Mixta del ICDA y del Coro Masculino Julio Alberto Hernández.\n\n" +
                    "\uD83D\uDD7A Además, no te pierdas la presentación de una muestra didáctica de Danza Folclórica: Ritmo y Sabor Dominicano, a cargo de la Dirección de Educación y Formación Artística Especializada (DEFAE) y la Escuela Nacional de Danza.\n\n" +
                    "\uD83C\uDF9F\uFE0F Entrada libre\n\n" +
                    "¡Te esperamos para vivir una velada llena de cultura y tradición dominicana!\n\n",
            "celebra_dominicanidad",
            "Celebrar la Dominicanidad",
            "¡Celebra la dominicanidad con nosotros! La Dirección Cultural del Instituto Cultural Domínico Americano te invita a formar parte de esta actividad en el Mes de la Independencia de la República Dominicana, con el apoyo de la Dirección General de Bellas Artes.",
            "Auditorio Patrick N. Hughson",
            "24/07/2024 ",
            "8:00AM"
        ),
        EventoCultural(
            3,
            "\uD83D\uDCAB\uD83C\uDF84 El Instituto Cultural Domínico Americano, desde su Dirección Cultural, les invita a una celebración especial de cierre de año, con la participación de:\n\n" +
                    "\uD83C\uDFAD\uD83D\uDCC3 Teatro Taller ICDA, presentando la obra “Habitación Oscura” del autor T. Williams, bajo la dirección de Jorge Santiago.\n\n" +
                    "\uD83C\uDF99\uFE0F\uD83E\uDDD4\u200D♂\uFE0F Coro Masculino JAH, con su director, el Sr. Henry Cordero.\n\n" +
                    "\uD83C\uDF99\uFE0F\uD83D\uDC69 Coral Mixta del ICDA, con su director, el Sr. Viterbo Peña.\n\n" +
                    "\uD83D\uDDD3\uFE0F⏰\uD83D\uDCCD Únanse a nosotros este miércoles 6 de diciembre de 2023, a las 6:00 p.m. en el Auditorio Patrick N. Hughson para disfrutar de una noche llena de arte, música y teatro, y despedir este año de la mejor manera.\n\n" +
                    "¡Les esperamos! ✨\uD83C\uDF84\n\n",
            "navidad_teatro",
            "Navidad y Teatro",
            "El Instituto Cultural Domínico Americano, desde su Dirección Cultural, les invita a una celebración especial de cierre de año.",
            "Auditorio Patrick N. Hughson",
            "24/07/2024 ",
            "5:00PM"
        )
    )

    private lateinit var rvEventosCulturales: RecyclerView
    private lateinit var eventosCulturalesAdapter: EventosCulturalesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_eventos_culturales)

        initComponents()
        initUI()
    }

    private fun initComponents() {
        rvEventosCulturales = findViewById(R.id.rvEventosCulturales)
    }

    private fun initUI() {
        eventosCulturalesAdapter = EventosCulturalesAdapter(this, eventosCulturales)
        rvEventosCulturales.layoutManager = LinearLayoutManager(this)
        rvEventosCulturales.adapter = eventosCulturalesAdapter

    }

    fun getEventoCulturalById(eventId: Int): EventoCultural? {
        return eventosCulturales.find { it.eventId == eventId }
    }
}