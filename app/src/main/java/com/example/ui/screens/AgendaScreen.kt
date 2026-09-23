package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.EventTimeCategory
import com.example.data.model.FestivalDay
import com.example.data.model.FestivalEvent
import com.example.data.model.FestivalVenue
import com.example.ui.components.EventItemCard
import com.example.ui.theme.BrandRed
import com.example.ui.theme.FilmBlack
import com.example.ui.theme.FilmCoal
import com.example.ui.theme.FilmGray
import com.example.ui.theme.PaperBase
import com.example.ui.theme.PaperMuted
import com.example.ui.theme.PaperTint
import com.example.ui.theme.PrimaryRed
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.viewmodel.FestivalNavTab

private const val STAMP_25_URL =
    "https://lh3.googleusercontent.com/aida-public/AB6AXuBrtFB06iOdZEuAK_V3mLbKifM_BPaNCR3NiXjI32QcrGi3gmnyESmKcooBewaCAHTz9woDDmn-WXm6nqrkNna2fY8A5KXMfg_Fxu98hphpba0jurhFROfSlldRiwEW2wXytwZNa5H_UYbLN0H8ugM_vTJLdvRRZiuF7scu7xTSZRd1R0Q7TAbjIkMl1XNS-OxoTXh2VgNzTSE0CbLevXgQF3z7MJYS4hwJpS8zcsp2vFbWbZACEbdl"

@Composable
fun AgendaScreen(
    days: List<FestivalDay>,
    selectedDayId: String,
    onSelectDay: (String) -> Unit,
    events: List<FestivalEvent>,
    venues: List<FestivalVenue>,
    selectedTimeCategory: EventTimeCategory,
    onSelectTimeCategory: (EventTimeCategory) -> Unit,
    selectedCategoryKey: String,
    onSelectCategoryKey: (String) -> Unit,
    onEventClick: (FestivalEvent) -> Unit,
    onToggleFavorite: (FestivalEvent) -> Unit,
    savedFavoritesCount: Int,
    onNavigateToRoute: () -> Unit,
    onOpenTicketInfo: () -> Unit,
    onOpenVenuesInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentDay = days.find { it.id == selectedDayId } ?: days.first()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Sticky/Top Day Selector Navigation
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PaperBase)
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                            text = "PROGRAMA OFICIAL",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryRed
                        )
                        Text(
                            text = currentDay.dateTitle,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(FilmBlack, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "DÍA ${currentDay.dayIndex}/8",
                            color = PureWhite,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // 8 Days Rail
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    days.forEach { day ->
                        val isSelected = day.id == selectedDayId
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onSelectDay(day.id) }
                                .testTag("agenda_day_${day.id}"),
                            color = if (isSelected) PrimaryRed else SurfaceContainerLowest,
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
                                    fontSize = 17.sp,
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

        // Time Filters & Category Chips
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Time of Day
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val timeOptions = listOf(
                        Pair(EventTimeCategory.ALL, "Todos los horarios"),
                        Pair(EventTimeCategory.MORNING, "Mañana (10:00 - 14:00)"),
                        Pair(EventTimeCategory.AFTERNOON, "Tarde (17:00 - 20:00)"),
                        Pair(EventTimeCategory.NIGHT, "Noche (20:00+)")
                    )

                    timeOptions.forEach { (cat, label) ->
                        val isSelected = selectedTimeCategory == cat
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectTimeCategory(cat) },
                            label = {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = FilmCoal,
                                selectedLabelColor = PureWhite
                            )
                        )
                    }
                }

                // Category Filter
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val categories = listOf(
                        Pair("todos", "Todas las actividades"),
                        Pair("oficial", "Competición Oficial"),
                        Pair("jica", "JICA Industria"),
                        Pair("especial", "Homenajes & Largos"),
                        Pair("asturias", "Sección Asturias"),
                        Pair("concierto", "Música & Cineconciertos")
                    )

                    categories.forEach { (key, label) ->
                        val isSelected = selectedCategoryKey == key
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectCategoryKey(key) },
                            label = {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PrimaryRed,
                                selectedLabelColor = PureWhite
                            )
                        )
                    }
                }
            }
        }

        // 25 Years Milestone & Box Office Callout
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                shape = RoundedCornerShape(8.dp),
                color = PaperBase,
                border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AsyncImage(
                        model = STAMP_25_URL,
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "25 AÑOS DE CELULOIDE",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryRed
                        )
                        Text(
                            text = "Recuerda: retirada de entradas gratuitas a partir de las 16:30 H en la Casa Municipal de Cultura.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FilmGray
                        )
                    }
                    IconButton(onClick = onOpenTicketInfo) {
                        Icon(
                            imageVector = Icons.Default.ConfirmationNumber,
                            contentDescription = "Taquilla",
                            tint = PrimaryRed
                        )
                    }
                }
            }
        }

        // Pinned Mi Ruta Quick Banner if user has saved events
        if (savedFavoritesCount > 0) {
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .clickable { onNavigateToRoute() },
                    shape = RoundedCornerShape(8.dp),
                    color = FilmBlack
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = null,
                                tint = BrandRed
                            )
                            Column {
                                Text(
                                    text = "MI RUTA FESTIVALERA",
                                    color = PureWhite,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$savedFavoritesCount evento(s) guardado(s)",
                                    color = PureWhite.copy(alpha = 0.7f),
                                    fontSize = 10.sp
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Ver mi ruta",
                                color = BrandRed,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = BrandRed,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        // Events List for the Day
        if (events.isEmpty()) {
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = PaperBase
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = null,
                            tint = FilmGray,
                            modifier = Modifier.size(32.dp)
                        )
                        Text(
                            text = "No se encontraron eventos con estos filtros",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Prueba a seleccionar 'Todos los horarios' o 'Todas las actividades'.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FilmGray
                        )
                    }
                }
            }
        } else {
            items(events, key = { it.id }) { event ->
                EventItemCard(
                    event = event,
                    onEventClick = { onEventClick(event) },
                    onToggleFavorite = { onToggleFavorite(event) },
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        }

        // Sedes Oficiales 2x2 Grid
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SEDES OFICIALES",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = onOpenVenuesInfo) {
                        Text(
                            text = "Ver detalles",
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryRed
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    VenueMiniCard(
                        name = "Casa de Cultura",
                        address = "Pl. Domingo Álvarez Acebal, 2",
                        badge = "Sede Central",
                        modifier = Modifier.weight(1f),
                        onClick = onOpenVenuesInfo
                    )
                    VenueMiniCard(
                        name = "Teatro Palacio Valdés",
                        address = "C/ Palacio Valdés, 3",
                        badge = "Clausura",
                        modifier = Modifier.weight(1f),
                        onClick = onOpenVenuesInfo
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    VenueMiniCard(
                        name = "Factoría Cultural",
                        address = "Avda. Portugal, 13",
                        badge = "JICA Industria",
                        modifier = Modifier.weight(1f),
                        onClick = onOpenVenuesInfo
                    )
                    VenueMiniCard(
                        name = "La Caset-AA",
                        address = "Plaza Álvarez Acebal",
                        badge = "Encuentros",
                        modifier = Modifier.weight(1f),
                        onClick = onOpenVenuesInfo
                    )
                }
            }
        }
    }
}

@Composable
private fun VenueMiniCard(
    name: String,
    address: String,
    badge: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(6.dp),
        color = SurfaceContainerLowest,
        border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(BrandRed, RoundedCornerShape(3.dp))
                    .padding(horizontal = 5.dp, vertical = 1.dp)
            ) {
                Text(
                    text = badge.uppercase(),
                    color = PureWhite,
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Text(
                text = address,
                style = MaterialTheme.typography.bodySmall,
                color = FilmGray,
                fontSize = 10.sp
            )
        }
    }
}
