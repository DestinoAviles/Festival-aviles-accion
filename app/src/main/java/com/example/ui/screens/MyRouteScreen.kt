package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FestivalEvent
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

@Composable
fun MyRouteScreen(
    favoriteEvents: List<FestivalEvent>,
    onEventClick: (FestivalEvent) -> Unit,
    onToggleFavorite: (FestivalEvent) -> Unit,
    onNavigateToAgenda: () -> Unit,
    onOpenTicketModal: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PaperBase)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ITINERARIO PERSONAL",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryRed
                    )
                    Box(
                        modifier = Modifier
                            .background(BrandRed, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${favoriteEvents.size} EVENTOS",
                            color = PureWhite,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Text(
                    text = "Mi Ruta Festivalera",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Organiza tu semana en Avilés Acción guardando tus películas, mesas redondas y cineconciertos favoritos.",
                    style = MaterialTheme.typography.bodySmall,
                    color = FilmGray
                )
            }
        }

        // Box office notice for saved events
        if (favoriteEvents.isNotEmpty()) {
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .clickable { onOpenTicketModal() },
                    shape = RoundedCornerShape(8.dp),
                    color = PaperBase,
                    border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ConfirmationNumber,
                            contentDescription = null,
                            tint = BrandRed,
                            modifier = Modifier.size(24.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Paso previo a tus sesiones:",
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                color = FilmCoal
                            )
                            Text(
                                text = "Retira tus entradas gratuitas el mismo día desde las 16:30 H en la taquilla de la Casa de Cultura.",
                                style = MaterialTheme.typography.bodySmall,
                                color = FilmGray
                            )
                        }
                    }
                }
            }
        }

        // Empty State or List
        if (favoriteEvents.isEmpty()) {
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = SurfaceContainerLowest,
                    border = androidx.compose.foundation.BorderStroke(1.dp, PaperMuted)
                ) {
                    Column(
                        modifier = Modifier.padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(PaperTint, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.BookmarkBorder,
                                contentDescription = null,
                                tint = PrimaryRed,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Text(
                            text = "Aún no tienes eventos en tu ruta",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Explora la Agenda oficial o las Secciones y pulsa el icono de marcapáginas para guardar tus sesiones preferidas.",
                            style = MaterialTheme.typography.bodySmall,
                            color = FilmGray
                        )
                        Button(
                            onClick = onNavigateToAgenda,
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Ir a la Agenda",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        } else {
            items(favoriteEvents, key = { it.id }) { event ->
                EventItemCard(
                    event = event,
                    onEventClick = { onEventClick(event) },
                    onToggleFavorite = { onToggleFavorite(event) },
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        }
    }
}
