package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.FestivalDay
import com.example.data.model.FestivalEvent
import com.example.data.model.FestivalNews
import com.example.data.model.FestivalSection
import com.example.ui.components.EventItemCard
import com.example.ui.theme.BrandRed
import com.example.ui.theme.BrandRedDark
import com.example.ui.theme.FilmBlack
import com.example.ui.theme.FilmCoal
import com.example.ui.theme.FilmGray
import com.example.ui.theme.GoldHonor
import com.example.ui.theme.PaperBase
import com.example.ui.theme.PaperMuted
import com.example.ui.theme.PaperTint
import com.example.ui.theme.PrimaryRed
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.viewmodel.FestivalNavTab

private const val CARTEL_URL =
    "https://lh3.googleusercontent.com/aida/AEtjO1UdWHc9UCeBlh6CHBf442UhGzsAzMZuB2ZaD4pFnaA3ef0DLMuyRc-ug3PAH4qG-17gW3BVMUEv3n1qIGfLOZuCQeJx0CoZxAh4NuMBV0B-aqcfWIFzMnazPG3XRvqHU7sHHGkKShCKohbUCsCrrj32r1_koI8MjaDqm2_lGGr1je62kBMkOjhFnSWb22zFTubrZnyf38OKZJh5kDE9w0WspclVszvm90wd0M1wMDvt5oQHCkJFGQHw0Q"

private const val STAMP_25_URL =
    "https://lh3.googleusercontent.com/aida-public/AB6AXuBrtFB06iOdZEuAK_V3mLbKifM_BPaNCR3NiXjI32QcrGi3gmnyESmKcooBewaCAHTz9woDDmn-WXm6nqrkNna2fY8A5KXMfg_Fxu98hphpba0jurhFROfSlldRiwEW2wXytwZNa5H_UYbLN0H8ugM_vTJLdvRRZiuF7scu7xTSZRd1R0Q7TAbjIkMl1XNS-OxoTXh2VgNzTSE0CbLevXgQF3z7MJYS4hwJpS8zcsp2vFbWbZACEbdl"

@Composable
fun HomeScreen(
    days: List<FestivalDay>,
    selectedDayId: String,
    onSelectDay: (String) -> Unit,
    dayEvents: List<FestivalEvent>,
    sections: List<FestivalSection>,
    newsList: List<FestivalNews>,
    onEventClick: (FestivalEvent) -> Unit,
    onToggleFavorite: (FestivalEvent) -> Unit,
    onNavigateTab: (FestivalNavTab) -> Unit,
    onNewsClick: (FestivalNews) -> Unit,
    onOpenPdfModal: () -> Unit,
    onOpenTicketModal: () -> Unit,
    onOpenVenuesModal: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Section: Archival paper style with 25th Anniversary
        item {
            HeroFestivalSection(
                onExploreAgenda = { onNavigateTab(FestivalNavTab.AGENDA) },
                onOpenPdf = onOpenPdfModal,
                onOpenTickets = onOpenTicketModal
            )
        }

        // Agenda Diaria Section with interactive day tabs
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "AGENDA DIARIA",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Selecciona un día del festival (10 al 17 Oct)",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = FilmGray
                        )
                    }
                    TextButton(
                        onClick = { onNavigateTab(FestivalNavTab.AGENDA) }
                    ) {
                        Text(
                            text = "Ver Todo",
                            color = PrimaryRed,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = PrimaryRed,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                // Day Selector Horizontal Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    days.forEach { day ->
                        val isSelected = day.id == selectedDayId
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onSelectDay(day.id) }
                                .testTag("home_day_${day.id}"),
                            color = if (isSelected) PrimaryRed else PaperBase,
                            shape = RoundedCornerShape(8.dp),
                            border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = day.dayShort,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) PureWhite.copy(alpha = 0.8f) else FilmGray
                                )
                                Text(
                                    text = day.dayNumber,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) PureWhite else FilmCoal
                                )
                            }
                        }
                    }
                }
            }
        }

        // Selected Day Events List
        if (dayEvents.isEmpty()) {
            item {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = PaperBase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                ) {
                    Text(
                        text = "No hay eventos programados para este filtro.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = FilmGray
                    )
                }
            }
        } else {
            items(dayEvents, key = { it.id }) { event ->
                EventItemCard(
                    event = event,
                    onEventClick = { onEventClick(event) },
                    onToggleFavorite = { onToggleFavorite(event) },
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        }

        // Secciones 2026 Carousel
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "SECCIONES 2026",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Catálogo completo de categorías y proyecciones",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = FilmGray
                        )
                    }
                    TextButton(
                        onClick = { onNavigateTab(FestivalNavTab.SECCIONES) }
                    ) {
                        Text(
                            text = "Catálogo",
                            color = PrimaryRed,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = PrimaryRed,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(sections, key = { it.id }) { section ->
                        SectionCarouselCard(
                            section = section,
                            onClick = { onNavigateTab(FestivalNavTab.SECCIONES) }
                        )
                    }
                }
            }
        }

        // Quick Badges Grid from HTML
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "DESTACADOS DEL 25º CERTAMEN",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickBadgeCard(
                        title = "12 Obras",
                        subtitle = "Sección Asturias",
                        tag = "Autores Regionales",
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateTab(FestivalNavTab.SECCIONES) }
                    )
                    QuickBadgeCard(
                        title = "GIFF México",
                        subtitle = "Festival Invitado",
                        tag = "Cortometrajes",
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateTab(FestivalNavTab.SECCIONES) }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickBadgeCard(
                        title = "I JICA",
                        subtitle = "Industria & Pitching",
                        tag = "Factoría Cultural",
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateTab(FestivalNavTab.SECCIONES) }
                    )
                    QuickBadgeCard(
                        title = "Premio Honor",
                        subtitle = "Isabel Coixet",
                        tag = "Pelayo Gutiérrez",
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateTab(FestivalNavTab.NOTICIAS) }
                    )
                }
            }
        }

        // News & Editorial Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "ÚLTIMAS NOTICIAS",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Actualidad y comunicados del certamen",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            color = FilmGray
                        )
                    }
                    TextButton(onClick = { onNavigateTab(FestivalNavTab.NOTICIAS) }) {
                        Text(
                            text = "Ver Todas",
                            color = PrimaryRed,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                val leadNews = newsList.firstOrNull()
                if (leadNews != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNewsClick(leadNews) },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column {
                            AsyncImage(
                                model = leadNews.imageUrl,
                                contentDescription = leadNews.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier.padding(14.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(BrandRed, RoundedCornerShape(3.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = leadNews.tag.uppercase(),
                                            color = PureWhite,
                                            fontSize = 9.sp,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Text(
                                        text = leadNews.date,
                                        fontSize = 11.sp,
                                        fontFamily = FontFamily.Monospace,
                                        color = FilmGray
                                    )
                                }
                                Text(
                                    text = leadNews.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = leadNews.summary,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        // Sedes & Taquilla Information Card
        item {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = PaperBase,
                border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = BrandRed
                        )
                        Text(
                            text = "SEDES & TAQUILLA DEL FESTIVAL",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "Casa Municipal de Cultura • Factoría Cultural • Teatro Palacio Valdés • La Caset-AA",
                        style = MaterialTheme.typography.bodySmall,
                        color = FilmCoal
                    )
                    Text(
                        text = "Todas las sesiones son de entrada libre hasta completar aforo. Retirada de entradas en taquilla desde las 16:30 H.",
                        fontSize = 11.sp,
                        color = FilmGray,
                        lineHeight = 16.sp
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Button(
                            onClick = onOpenVenuesModal,
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Ver Sedes",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        OutlinedButton(
                            onClick = onOpenTicketModal,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Info Taquilla",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Festival Footer
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FilmBlack)
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "AVILÉS ACCIÓN FILM FESTIVAL",
                    color = PureWhite,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.05.sp
                )
                Text(
                    text = "25º Festival de Cortometrajes • 10-17 Octubre 2026",
                    color = PureWhite.copy(alpha = 0.7f),
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "Ayuntamiento de Avilés • Principado de Asturias • Laboral Cinemateca",
                    color = PureWhite.copy(alpha = 0.5f),
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Composable
private fun HeroFestivalSection(
    onExploreAgenda: () -> Unit,
    onOpenPdf: () -> Unit,
    onOpenTickets: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = PaperBase,
        border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Milestone Stamp & Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(BrandRed, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "25 AÑOS",
                            color = PureWhite,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "10 AL 17 OCT 2026",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = FilmCoal
                    )
                }

                // 25 Balloon Stamp
                AsyncImage(
                    model = STAMP_25_URL,
                    contentDescription = "25 Años",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            // Headline
            Text(
                text = "25 AÑOS DE CORTOMETRAJES Y VANGUARDIA EN AVILÉS",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = FilmBlack,
                lineHeight = 26.sp
            )

            // Official Cartel Image
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = CARTEL_URL,
                    contentDescription = "Cartel Oficial 25 Aniversario",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Editorial Quote
            Text(
                text = "Avilés se convierte en el epicentro del formato corto: 33 películas a concurso, I Jornadas de Industria Asturiana (JICA), cineconciertos, y homenaje de honor a Isabel Coixet.",
                style = MaterialTheme.typography.bodySmall,
                color = FilmGray,
                lineHeight = 17.sp
            )

            // Free Admission Notice
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenTickets() }
                    .background(PaperTint, RoundedCornerShape(6.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ConfirmationNumber,
                    contentDescription = null,
                    tint = BrandRed,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Entrada gratuita para todas las sesiones (retirada en taquilla)",
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = FilmCoal
                )
            }

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onExploreAgenda,
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ver Agenda",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }

                OutlinedButton(
                    onClick = onOpenPdf,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Programa PDF",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionCarouselCard(
    section: FestivalSection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(220.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                AsyncImage(
                    model = section.imageUrl,
                    contentDescription = section.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .background(FilmBlack.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = section.worksCount,
                        color = PureWhite,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = section.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = section.subtitle,
                    fontSize = 11.sp,
                    color = PrimaryRed,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = section.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = FilmGray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun QuickBadgeCard(
    title: String,
    subtitle: String,
    tag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() },
        color = SurfaceContainerLowest,
        shape = RoundedCornerShape(6.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(BrandRed, RoundedCornerShape(3.dp))
                    .padding(horizontal = 5.dp, vertical = 1.dp)
            ) {
                Text(
                    text = tag.uppercase(),
                    color = PureWhite,
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                color = FilmGray
            )
        }
    }
}
