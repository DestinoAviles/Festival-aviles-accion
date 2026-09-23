package com.example.data.repository

import com.example.data.local.FavoriteDao
import com.example.data.local.FavoriteEventEntity
import com.example.data.model.EventTimeCategory
import com.example.data.model.FestivalDay
import com.example.data.model.FestivalEvent
import com.example.data.model.FestivalNews
import com.example.data.model.FestivalSection
import com.example.data.model.FestivalVenue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FestivalRepository(private val favoriteDao: FavoriteDao) {

    val festivalDays: List<FestivalDay> = listOf(
        FestivalDay("sab10", "SÁB", "10", "Sábado 10 de Octubre", 1),
        FestivalDay("dom11", "DOM", "11", "Domingo 11 de Octubre", 2),
        FestivalDay("lun12", "LUN", "12", "Lunes 12 de Octubre", 3),
        FestivalDay("mar13", "MAR", "13", "Martes 13 de Octubre", 4),
        FestivalDay("mie14", "MIÉ", "14", "Miércoles 14 de Octubre", 5),
        FestivalDay("jue15", "JUE", "15", "Jueves 15 de Octubre", 6),
        FestivalDay("vie16", "VIE", "16", "Viernes 16 de Octubre", 7),
        FestivalDay("sab17", "SÁB", "17", "Sábado 17 de Octubre", 8),
    )

    private val baseEvents: List<FestivalEvent> = listOf(
        // Sábado 10
        FestivalEvent(
            id = "sab10_pitching",
            dayId = "sab10",
            dayLabel = "Sábado 10 Octubre",
            time = "11:00 H",
            timeCategory = EventTimeCategory.MORNING,
            category = "JICA Industria",
            categoryKey = "jica",
            title = "Pitching de Cortometrajes en Desarrollo",
            description = "I Jornadas de Industria del Cortometraje Asturiano. 8 proyectos seleccionados en defensa pública frente a comisiones de producción y distribución.",
            venue = "Factoría Cultural",
            venueAddress = "Avda. Portugal, 13",
            duration = "120 MIN",
            tag = "Acceso Profesional / Aforo"
        ),
        FestivalEvent(
            id = "sab10_coixet_intro",
            dayId = "sab10",
            dayLabel = "Sábado 10 Octubre",
            time = "18:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Premio Homenaje",
            categoryKey = "especial",
            title = "Mi vida sin mí (Proyección Inaugural)",
            description = "Dir. Isabel Coixet (2003). Con Sarah Polley, Mark Ruffalo y Leonor Watling. Presentación con la realizadora invitada.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "106′",
            tag = "Retrospectiva de Honor",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCg-TLLlUJw3ih8jLLOz8E_9FyzIvx3ogQ2aMi394aQxcEIcGL5nyz8Gvfm7pRvxpauZ-03sunNOvqxMuvFEAxL0G67vxRnLEDK5VVVXUIYK-jeJmrjsxR-_AWAFk0hcyDxF8c0d_7oJLgk2TGEAjiaNuzsqZImSTH3fgb3O6nGhUQ1UnSkQNus7OrnXjY8Y2RC9bNCNsPuxc4a9Xaw9FG8hSIBNdBV2Bxbzi5uSb_ntinZbAcW8KCo"
        ),
        FestivalEvent(
            id = "sab10_preboda",
            dayId = "sab10",
            dayLabel = "Sábado 10 Octubre",
            time = "19:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Cineconcierto",
            categoryKey = "concierto",
            title = "PREBODA + Cineconcierto 'El Viaje Cósmico'",
            description = "Clásico silente de ciencia ficción soviético (1936) sonorizado y musicalizado íntegramente en directo por sintetizadores analógicos en la carpa festivalera.",
            venue = "La Caset-AA",
            venueAddress = "Plaza Domingo Álvarez Acebal",
            duration = "75 MIN",
            tag = "Gala Inaugural"
        ),
        FestivalEvent(
            id = "sab10_oficial1_preview",
            dayId = "sab10",
            dayLabel = "Sábado 10 Octubre",
            time = "20:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Sección Oficial",
            categoryKey = "oficial",
            title = "Cortometrajes a Concurso + Q&A",
            description = "Pase de apertura con cortos en competición y presencia de directores emergentes para coloquio con el público.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "84′",
            tag = "Competición Nacional",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBkJTi_uhzx4qLL36fTCyo6L-SkA17OeMvl04Dj51SG018LVzbiToCgy2fEwQNL9iFsJG_oA3ksU3G3PseVOuX1ALIdZsXRAFymXGv_l8n3rTg2UNjt3Cw1ec6HpdY2yo9RcR-I3Pw1Yr4NKh-6pe7E2YZpI2C_YdBEkQA329BUmrKN6OpGRvUSte3TlS5BCHmbmg3n4TDnMM-TadME0ZUlbGN3qxsl6W9iEq-mSWtoAh0wrdYdFuwp"
        ),
        FestivalEvent(
            id = "sab10_concierto",
            dayId = "sab10",
            dayLabel = "Sábado 10 Octubre",
            time = "21:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Música en Vivo",
            categoryKey = "concierto",
            title = "Concierto DRB",
            description = "Concierto Preboda en el punto de encuentro festivalero de La Caset-AA. Guitarras garajeras y baile en el epicentro urbano del festival.",
            venue = "La Caset-AA",
            venueAddress = "Plaza Domingo Álvarez Acebal",
            duration = "90 MIN",
            tag = "Entrada Libre"
        ),

        // Domingo 11
        FestivalEvent(
            id = "dom11_coixet",
            dayId = "dom11",
            dayLabel = "Domingo 11 Octubre",
            time = "18:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Ciclo Isabel Coixet",
            categoryKey = "especial",
            title = "Largo: 'Mi vida sin mí' (106')",
            description = "Proyección homenaje a la Premio de Honor 2026. Protagonizada por Sarah Polley, Scott Speedman y Deborah Harry.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "106 MIN",
            tag = "VOSE"
        ),
        FestivalEvent(
            id = "dom11_volver",
            dayId = "dom11",
            dayLabel = "Domingo 11 Octubre",
            time = "20:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Largometraje",
            categoryKey = "especial",
            title = "Largo: 'Volver a casa tan tarde' (70')",
            description = "Sesión especial de cine de autor independiente iberoamericano.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "70 MIN",
            tag = "70 MIN"
        ),

        // Lunes 12
        FestivalEvent(
            id = "lun12_vermu",
            dayId = "lun12",
            dayLabel = "Lunes 12 Octubre",
            time = "13:00 H",
            timeCategory = EventTimeCategory.MORNING,
            category = "Vermú Festivalero",
            categoryKey = "concierto",
            title = "Vermú DJ Dr.Portatil",
            description = "Sesión musical de vinilos y ambiente cinéfilo en el punto de encuentro exterior.",
            venue = "La Caset-AA",
            venueAddress = "Plaza Domingo Álvarez Acebal",
            duration = "90 MIN",
            tag = "Exterior"
        ),
        FestivalEvent(
            id = "lun12_linces",
            dayId = "lun12",
            dayLabel = "Lunes 12 Octubre",
            time = "18:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Largometraje",
            categoryKey = "especial",
            title = "Los linces, puro acero y rock & roll (98')",
            description = "Documental musical sobre la emblemática banda asturiana y la memoria industrial de la comarca.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "98 MIN",
            tag = "98 MIN"
        ),
        FestivalEvent(
            id = "lun12_animacion",
            dayId = "lun12",
            dayLabel = "Lunes 12 Octubre",
            time = "20:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Competición",
            categoryKey = "animacion",
            title = "Sección Animación Oficial",
            description = "Pase completo de cortometrajes animados nacionales e internacionales en concurso. Stop-motion, 2D y 3D experimental.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "80 MIN",
            tag = "Competición Oficial"
        ),

        // Martes 13
        FestivalEvent(
            id = "mar13_cineforum",
            dayId = "mar13",
            dayLabel = "Martes 13 Octubre",
            time = "13:00 H",
            timeCategory = EventTimeCategory.MORNING,
            category = "Educación",
            categoryKey = "jica",
            title = "Cineforum Institutos de Avilés",
            description = "Formación y debate audiovisual para alumnado de secundaria con directores y educadores.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "90 MIN",
            tag = "Docente"
        ),
        FestivalEvent(
            id = "mar13_verguenza",
            dayId = "mar13",
            dayLabel = "Martes 13 Octubre",
            time = "18:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Largometraje",
            categoryKey = "especial",
            title = "Largo: 'Vergüenza' (103')",
            description = "Sesión especial de cine social y debate posterior con el equipo técnico de la película.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "103 MIN",
            tag = "Coloquio"
        ),
        FestivalEvent(
            id = "mar13_giff",
            dayId = "mar13",
            dayLabel = "Martes 13 Octubre",
            time = "20:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Festival Invitado",
            categoryKey = "giff",
            title = "Sección GIFF Guanajuato (México)",
            description = "Selección del prestigioso festival mexicano hermanado con Avilés Acción, con los mejores cortos de ficción y documental azteca.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "85 MIN",
            tag = "Internacional"
        ),

        // Miércoles 14
        FestivalEvent(
            id = "mie14_masterclass",
            dayId = "mie14",
            dayLabel = "Miércoles 14 Octubre",
            time = "10:00 H",
            timeCategory = EventTimeCategory.MORNING,
            category = "JICA",
            categoryKey = "jica",
            title = "Masterclass Fotografía & Masterclass Producción",
            description = "Impartidas por directores de fotografía galardonados en los Premios Goya y jefes de producción de cine independiente.",
            venue = "Factoría Cultural",
            venueAddress = "Avda. Portugal, 13",
            duration = "120 MIN",
            tag = "Formación"
        ),
        FestivalEvent(
            id = "mie14_becky",
            dayId = "mie14",
            dayLabel = "Miércoles 14 Octubre",
            time = "18:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Largo",
            categoryKey = "especial",
            title = "Largo: 'Monos como Becky' (81')",
            description = "Obra maestra de Joaquín Jordá recuperada en copia restaurada en alta definición.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "81 MIN",
            tag = "Retrospectiva"
        ),
        FestivalEvent(
            id = "mie14_documental",
            dayId = "mie14",
            dayLabel = "Miércoles 14 Octubre",
            time = "20:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Competición",
            categoryKey = "documental",
            title = "Sección Documental Oficial",
            description = "Pase a concurso con los 5 cortometrajes documentales seleccionados en 2026. Miradas incisivas a la realidad social.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "90 MIN",
            tag = "Sesión 90'"
        ),

        // Jueves 15
        FestivalEvent(
            id = "jue15_vivero",
            dayId = "jue15",
            dayLabel = "Jueves 15 Octubre",
            time = "10:00 H",
            timeCategory = EventTimeCategory.MORNING,
            category = "JICA Industria",
            categoryKey = "jica",
            title = "Vivero de Historias Audiovisuales + Mesa Redonda",
            description = "Nuevos formatos digitales, inteligencia artificial ética y vías de financiación pública en el cortometraje asturiano.",
            venue = "Factoría Cultural",
            venueAddress = "Avda. Portugal, 13",
            duration = "120 MIN",
            tag = "Industria"
        ),
        FestivalEvent(
            id = "jue15_asturias",
            dayId = "jue15",
            dayLabel = "Jueves 15 Octubre",
            time = "18:00 & 20:00 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Asturias Cine",
            categoryKey = "asturias",
            title = "Sección Asturias 1 & Asturias 2",
            description = "Doble sesión del mejor cine producido en el Principado con coloquio y presencia de todos los directores.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "110 MIN",
            tag = "Sesión Doble"
        ),

        // Viernes 16
        FestivalEvent(
            id = "vie16_pelayo",
            dayId = "vie16",
            dayLabel = "Viernes 16 Octubre",
            time = "10:00 H",
            timeCategory = EventTimeCategory.MORNING,
            category = "JICA Formación",
            categoryKey = "jica",
            title = "Masterclass Sonido con Pelayo Gutiérrez Álvarez",
            description = "El diseñador de sonido galardonado con 3 Premios Goya profundiza en la narrativa sonora y la atmósfera en el cine contemporáneo.",
            venue = "Factoría Cultural",
            venueAddress = "Avda. Portugal, 13",
            duration = "120 MIN",
            tag = "Goya Honor"
        ),
        FestivalEvent(
            id = "vie16_oficial1",
            dayId = "vie16",
            dayLabel = "Viernes 16 Octubre",
            time = "17:00 & 18:45 H",
            timeCategory = EventTimeCategory.AFTERNOON,
            category = "Competición",
            categoryKey = "oficial",
            title = "Sección Oficial 1 (84') & Oficial 2 (87')",
            description = "Los platos fuertes de la Sección Oficial en concurso con voto directo del público para el premio popular.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "171 MIN",
            tag = "Pase Oficial"
        ),
        FestivalEvent(
            id = "vie16_coixet_encuentro",
            dayId = "vie16",
            dayLabel = "Viernes 16 Octubre",
            time = "20:15 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Homenaje",
            categoryKey = "especial",
            title = "Encuentro Magistral Isabel Coixet",
            description = "Conversación abierta de la cineasta con el público y entrega solemne del galardón de honor 25 Aniversario.",
            venue = "Casa Municipal de Cultura",
            venueAddress = "Pl. Domingo Álvarez Acebal, 2",
            duration = "90 MIN",
            tag = "Imprescindible"
        ),

        // Sábado 17
        FestivalEvent(
            id = "sab17_clausura",
            dayId = "sab17",
            dayLabel = "Sábado 17 Octubre",
            time = "20:15 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Gala Clausura",
            categoryKey = "especial",
            title = "Banquete Nupcial • Gala de Clausura",
            description = "Entrega de premios del 25º Certamen Nacional, proyección de los cortometrajes ganadores y show conmemorativo especial.",
            venue = "Teatro Palacio Valdés",
            venueAddress = "C/ Palacio Valdés, 3",
            duration = "120 MIN",
            tag = "Teatro Principal"
        ),
        FestivalEvent(
            id = "sab17_fiesta",
            dayId = "sab17",
            dayLabel = "Sábado 17 Octubre",
            time = "00:00 H",
            timeCategory = EventTimeCategory.NIGHT,
            category = "Fin de Fiesta",
            categoryKey = "concierto",
            title = "Fiesta Clausura 25 Aniversario",
            description = "Celebración festiva de cierre con realizadores, jurado, equipo del festival y público.",
            venue = "Plaza's GastroPub",
            venueAddress = "Avilés Centro",
            duration = "180 MIN",
            tag = "Acceso Libre"
        )
    )

    val sections: List<FestivalSection> = listOf(
        FestivalSection(
            id = "sec_oficial",
            key = "oficial",
            title = "Sección Oficial",
            subtitle = "Nuevas Voces del Cine",
            description = "33 obras seleccionadas que condensan lo más audaz del panorama actual español e internacional, compitiendo por los premios del Jurado Oficial.",
            dateVenue = "Viernes 16 Oct • 17:00 & 18:45 • Casa Municipal de Cultura",
            worksCount = "33 Cortos",
            tag = "★ Competición",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1X_p92c4gef9fd1ef8mig9oxNFPgFOjDUVzrna1TGTFDYFogZ10elStHSwy34ITi3EkicHrqZ7fy_3sT5C_44kQKuaSnC31st0CpYAtdSIxEDU89hDmNmeE2fZ0I_FiJOgOLk2i_iTKI-rT-ikPNoSngPhdvT62qBLd6uDrAivDwl9pIfl4Iqi8BuhPbL028nPzGce68NLojU_dLJOpJV1IoCoEFv3aDNwkVGA5UWwQACtYq0uykivANKM",
            keywords = "seccion oficial nuevas voces 33 cortos competicion jurado ficcion cine contemporaneo"
        ),
        FestivalSection(
            id = "sec_jica",
            key = "jica",
            title = "Industria Asturiana (JICA)",
            subtitle = "I Jornadas de Industria del Cortometraje",
            description = "Pitching de proyectos emergentes asturianos, masterclasses de sonido con Pelayo Gutiérrez, dirección de fotografía y mesas redondas sobre coproducción.",
            dateVenue = "10, 14, 15 y 16 Oct • Factoría Cultural",
            worksCount = "I Edición",
            tag = "JICA Industria",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1W-xesc5QLvXFq-IDLsaVXBS7_dYJDBdXEt6DYqQ9XX8aAW27oBfi8W7ySC_U--_zn3QEWM3ykn7QsbQ9fAsLfZ6ouKVVhBJvFhRDeGxi8Vp7QN9mLFZicqHwpTf0kEcHSqGFbKp8r2_D8qYwqvfbbuXgjD3eleKhYB5shVRKXg33M187Z-5cKYCkIHV4SUUMH4lSi6sC2gHE0fKoQfKtrQ6DFCPnpLAM067fEFEmLB3b4mnRBXQ1paiQ",
            keywords = "jica industria jornadas cortometraje asturiano factoria cultural pitching masterclass coproduccion pelayo gutierrez"
        ),
        FestivalSection(
            id = "sec_asturias",
            key = "asturias",
            title = "Sección Asturias 1 & 2",
            subtitle = "Talento Regional",
            description = "12 títulos producidos en el Principado o dirigidos por realizadores asturianos que exploran el territorio, la identidad y nuevos lenguajes narrativos.",
            dateVenue = "Jueves 15 Oct • 18:00 & 20:00 • Casa Municipal de Cultura",
            worksCount = "12 Obras",
            tag = "Identidad & Territorio",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1WLj1s2JQC-JuTxI3cRMeWWcCffmeEsPo6lZcMuopZBljMdWxeati-P5iPGq45I4MDxvy6RH585EDYXM2DqW9XxkxuTYe_C6dqoYHxJs3mylqSFKMAuzQhoBMAKsOpOx9Zv53MRICj70TgC8do5Q1Z2_H8GGV_KAqMlpItdJJyuGkU8dWzfbMWYWXgkscLNTzlc4uBatrzgJgu0EBVGaTTjXyEEXspeQSEQxiCS4AQu15MRQiaR3x2GzQ",
            keywords = "seccion asturias talento asturiano principado autores locales cine astur"
        ),
        FestivalSection(
            id = "sec_animacion",
            key = "animacion",
            title = "Sección Animación",
            subtitle = "Vanguardias Visuales",
            description = "Un caleidoscopio visual con las técnicas más vanguardistas de la animación contemporánea mundial: stop-motion, técnica mixta y animación digital de autor.",
            dateVenue = "Lunes 12 Oct • 20:00 • Casa Municipal de Cultura",
            worksCount = "Animación",
            tag = "Internacional",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1U8thYDMa7tIVYHcdTmf2w7aZaQ1fDf89R2CIvctJq44Ome-b7h9sbENC66JrhaFR3QT1PqvifKw6E7epDbYqqucB8HG-16Fyf739mvEoBR2xdYbRBsJVDUMIOihpDkq2fUtibWrnWOlUD0U6LksNvj582OHZIg26_GyulPkUL_aYVD_fn0uncQGWC-9tYZxtwIAAEtVMfUINJy3tEkld5zpQ0Zv73rjmC5XsWji2a5a5PnYANaFAdOkKQ",
            keywords = "animacion nuevos formatos stop motion 2d 3d internacional vanguardias"
        ),
        FestivalSection(
            id = "sec_documental",
            key = "documental",
            title = "Sección Documental",
            subtitle = "Miradas a la Realidad",
            description = "5 obras no ficcionales seleccionadas que recorren temáticas sociales urgentes, memorias colectivas y biografías comprometidas.",
            dateVenue = "Miércoles 14 Oct • 20:00 • Casa Municipal de Cultura",
            worksCount = "5 Cortos",
            tag = "No Ficción",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1Vj0uyd7xQRDXi3SMCRhw7VJHHxQvWWoklvqm0OyFcIeJbueJ6Gt4-rp99qvtqBPENrwN7cPB14z11Y4bfrZA_n1wZybDIBX7RQbTxZ8D1gKPTr8CJetrnVSAhY62tzdGivrjwOj9tMAXogAC_lEu1m3UjTH8u-hfHV3w3kzJehbZVq5HPOP7nSS2kUqrTr0-xKP5AbayhakfLBP2HIo_o2Ih9urw6b30c1LLe2DMTR9ikDFgBwmh3rrnA",
            keywords = "documental no ficcion memoria realidad sociedad"
        ),
        FestivalSection(
            id = "sec_giff",
            key = "giff",
            title = "Ventana GIFF Guanajuato",
            subtitle = "Festival Hermano de México",
            description = "Alianza transatlántica con el certamen de cine más emblemático de México. Una cuidada selección de cortometrajes mexicanos premiados en su última edición.",
            dateVenue = "Martes 13 Oct • 20:00 • Casa Municipal de Cultura",
            worksCount = "México",
            tag = "Festival Invitado",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1Xi_UokgIyzR7HyB1ceASap1psJAEfwAt8DNq07naLGDy1mG_oj0lJqomA5uHVY98Pq8nVXHJf6QsLoC00ICkfe22743yPhDTJi_5sJNGCL7XVlbZPBh1rOODgUGvlgLlTK1BqIgfHez1rUv9N9JMFMmlMZHqQGF6zlJtCfjnBS0cSLsJ-a3AsGoRA_svzPUtw-xPCDkmSNIt21_BsW7XQfC6IzX175E7NSesfdWH9uDn5eAHu7sCFqPGw",
            keywords = "giff guanajuato mexico festival internacional intercambio alianza transatlantica"
        )
    )

    val newsList: List<FestivalNews> = listOf(
        FestivalNews(
            id = "noticia_1",
            date = "16 Sep 2026",
            tag = "Premio Homenaje 2026",
            title = "Avilés Acción celebra su 25º aniversario con un doble reconocimiento a Isabel Coixet y Pelayo Gutiérrez Álvarez",
            summary = "Avilés Acción conmemora este año su vigesimoquinto aniversario, que se celebrará del 10 al 17 de octubre, con el reconocimiento unánime a dos leyendas del cine español contemporáneo.",
            content = "El Festival de Cortometrajes Avilés Acción cumple un cuarto de siglo por todo lo alto. La cineasta Isabel Coixet recibirá el galardón de honor 25 Aniversario en un encuentro magistral con el público, acompañado de la proyección especial de 'Mi vida sin mí'. Asimismo, el diseñador de sonido Pelayo Gutiérrez Álvarez, ganador de 3 premios Goya por filmes como 'La trinchera infinita' y 'El reino', impartirá una clase magistral única para creadores audiovisuales asturianos.",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1UWGwvNS7kgkDyTztekDUFWPl9374sVc2sMcfHvIl-eC39aeKs5dj3cIdLge1o4y8rvD3KU0bupfdCv7tbobpCwwNCoo2ItIUxsHjPp_aGem_8DXPHspzF2sB13t9-ganx2wcTykBhLfd2Gv2e1_AMGidt-fg1h3JaznLN9QJQZD0IhsuSfycQDNMP6kJ2Tg_LxfDpU0XWqHRzfBl5BwfdtnP5LvwOKLD5iCuL_Ak3N4IqGlJwSllco-FA"
        ),
        FestivalNews(
            id = "noticia_2",
            date = "10 Sep 2026",
            tag = "Selección Oficial",
            title = "Selección de 33 cortometrajes que recorren las nuevas miradas del cine",
            summary = "Reúne nuevas voces, miradas singulares y una pluralidad de lenguajes procedentes de todo el estado y el ámbito internacional.",
            content = "El comité de selección ha evaluado más de 800 candidaturas para conformar una Sección Oficial compuesta por 33 títulos. Las piezas exploran géneros que van desde el drama intimista hasta la comedia negra, la ciencia ficción distópica y el ensayo documental, dando visibilidad tanto a directores consagrados como a voces de escuelas de cine.",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1Vj0uyd7xQRDXi3SMCRhw7VJHHxQvWWoklvqm0OyFcIeJbueJ6Gt4-rp99qvtqBPENrwN7cPB14z11Y4bfrZA_n1wZybDIBX7RQbTxZ8D1gKPTr8CJetrnVSAhY62tzdGivrjwOj9tMAXogAC_lEu1m3UjTH8u-hfHV3w3kzJehbZVq5HPOP7nSS2kUqrTr0-xKP5AbayhakfLBP2HIo_o2Ih9urw6b30c1LLe2DMTR9ikDFgBwmh3rrnA"
        ),
        FestivalNews(
            id = "noticia_3",
            date = "5 Ago 2026",
            tag = "Industria & Formación",
            title = "Avilés Acción estrena las I Jornadas de la Industria del Cortometraje (JICA)",
            summary = "Un espacio de encuentro, impulso profesional y coproducción para dinamizar el sector audiovisual asturiano.",
            content = "Por primera vez en sus 25 años de historia, Avilés Acción contará con un área de industria estructurada: JICA. Concebida para tejer redes entre realizadores, productores, distribuidoras y cadenas de televisión, acogerá sesiones de pitching con premios económicos para proyectos en desarrollo y foros de coproducción regional.",
            imageUrl = "https://lh3.googleusercontent.com/aida/AEtjO1Xi_UokgIyzR7HyB1ceASap1psJAEfwAt8DNq07naLGDy1mG_oj0lJqomA5uHVY98Pq8nVXHJf6QsLoC00ICkfe22743yPhDTJi_5sJNGCL7XVlbZPBh1rOODgUGvlgLlTK1BqIgfHez1rUv9N9JMFMmlMZHqQGF6zlJtCfjnBS0cSLsJ-a3AsGoRA_svzPUtw-xPCDkmSNIt21_BsW7XQfC6IzX175E7NSesfdWH9uDn5eAHu7sCFqPGw"
        )
    )

    val venues: List<FestivalVenue> = listOf(
        FestivalVenue(
            id = "v_casa_cultura",
            name = "Casa Municipal de Cultura",
            role = "Sede Central & Taquilla Principal",
            address = "Plaza Domingo Álvarez Acebal, 2",
            schedule = "Taquilla abierta desde 16:30 H cada día de sesión. Máx. 2 entradas por persona.",
            price = "0,00 € (Gratis hasta completar aforo)"
        ),
        FestivalVenue(
            id = "v_palacio_valdes",
            name = "Teatro Palacio Valdés",
            role = "Gala de Clausura & Entrega de Palmarés",
            address = "Calle Palacio Valdés, 3",
            schedule = "Sábado 17 de Octubre a las 20:15 H.",
            price = "Entrada libre con invitación previa en taquilla"
        ),
        FestivalVenue(
            id = "v_factoria",
            name = "Factoría Cultural",
            role = "Jornadas JICA & Masterclasses",
            address = "Avenida de Portugal, 13",
            schedule = "Sesiones de mañana (10:00 - 13:00 H). Acceso acreditado y estudiantes.",
            price = "Acceso libre hasta completar aforo"
        ),
        FestivalVenue(
            id = "v_caset",
            name = "La Caset-AA",
            role = "Encuentros, Vermú & Cineconciertos",
            address = "Plaza Domingo Álvarez Acebal (Carpa exterior)",
            schedule = "Abierta de 12:30 a 01:00 H.",
            price = "Acceso libre y gratuito"
        )
    )

    /**
     * Flow of all events with updated favorite status from the database.
     */
    val eventsFlow: Flow<List<FestivalEvent>> = favoriteDao.getAllFavoriteIds().map { favoriteIds ->
        val favSet = favoriteIds.toSet()
        baseEvents.map { event ->
            event.copy(isFavorite = favSet.contains(event.id))
        }
    }

    suspend fun toggleFavorite(eventId: String, currentFavorite: Boolean) {
        if (currentFavorite) {
            favoriteDao.removeFavorite(eventId)
        } else {
            favoriteDao.addFavorite(FavoriteEventEntity(eventId))
        }
    }
}
