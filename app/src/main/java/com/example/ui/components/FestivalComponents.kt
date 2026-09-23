package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.FestivalEvent
import com.example.data.model.FestivalNews
import com.example.data.model.FestivalVenue
import com.example.ui.theme.BrandRed
import com.example.ui.theme.BrandRedDark
import com.example.ui.theme.FilmBlack
import com.example.ui.theme.FilmCoal
import com.example.ui.theme.FilmGray
import com.example.ui.theme.PaperBase
import com.example.ui.theme.PaperMuted
import com.example.ui.theme.PaperTint
import com.example.ui.theme.PrimaryRed
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.TertiaryContainer
import com.example.ui.viewmodel.FestivalNavTab

private const val FESTIVAL_LOGO_URL =
    "https://lh3.googleusercontent.com/aida/AEtjO1X9QwgqX8gFEH6pGwgkGk6qgxVarH1tdZJZ2tw3ONNYCFDBY9cdspRhoM8KNh_6X8cWFxnoaH2ULNpatLFLbSU9uxgwAbPc_7tRRASg4EVbEY-zSCnzrCE83cd8qFIaGvR1VwEOH23ONAGuucQZHSHNa89rQXvi-cF597WW2qgkrcvNI7bhuQ8ZzC6FJRoG_swno0kRh7-5fNEP-QyNvwa0I_mSOoJ6lQ-SyECAaVQJN_I4ohHIX6Jz-nI"

@Composable
fun FestivalTopHeader(
    currentTab: FestivalNavTab,
    savedCount: Int,
    onTabSelected: (FestivalNavTab) -> Unit,
    onOpenTickets: () -> Unit,
    onOpenVenues: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.96f))
            .statusBarsPadding()
    ) {
        // Red 25th Anniversary Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BrandRed)
                .padding(horizontal = 14.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "25º ANIVERSARIO • 10-17 OCT 2026",
                color = PureWhite,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.06.sp
            )
            Box(
                modifier = Modifier
                    .background(BrandRedDark, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "EDICIÓN ESPECIAL",
                    color = PureWhite,
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Header Main Action Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo and Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabSelected(FestivalNavTab.INICIO) }
            ) {
                AsyncImage(
                    model = FESTIVAL_LOGO_URL,
                    contentDescription = "Logo Avilés Acción",
                    modifier = Modifier
                        .height(30.dp)
                        .width(42.dp),
                    contentScale = ContentScale.Fit
                )
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "AVILÉS ACCIÓN",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            letterSpacing = (-0.02).sp
                        )
                        Box(
                            modifier = Modifier
                                .background(BrandRed, RoundedCornerShape(3.dp))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "25º ANIV",
                                color = PureWhite,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "10-17 OCT 2026",
                            color = BrandRed,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "•",
                            color = FilmGray,
                            fontSize = 10.sp
                        )
                        Text(
                            text = when (currentTab) {
                                FestivalNavTab.INICIO -> "Inicio"
                                FestivalNavTab.AGENDA -> "Agenda"
                                FestivalNavTab.SECCIONES -> "Catálogo"
                                FestivalNavTab.NOTICIAS -> "Noticias"
                                FestivalNavTab.MI_RUTA -> "Mi Ruta"
                            },
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Quick Top Actions: Sedes & Mi Ruta
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                IconButton(
                    onClick = onOpenVenues,
                    modifier = Modifier.size(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Storefront,
                        contentDescription = "Sedes Oficiales",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(
                    onClick = onOpenTickets,
                    modifier = Modifier.size(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ConfirmationNumber,
                        contentDescription = "Información Taquilla",
                        tint = BrandRed,
                        modifier = Modifier.size(20.dp)
                    )
                }

                BadgedBox(
                    badge = {
                        if (savedCount > 0) {
                            Badge(
                                containerColor = BrandRed,
                                contentColor = PureWhite
                            ) {
                                Text(
                                    text = savedCount.toString(),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                ) {
                    IconButton(
                        onClick = { onTabSelected(FestivalNavTab.MI_RUTA) },
                        modifier = Modifier.size(38.dp)
                    ) {
                        Icon(
                            imageVector = if (savedCount > 0) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Mi Ruta Festivalera",
                            tint = if (savedCount > 0) BrandRed else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FestivalNoticeBanner(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryRed)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.ConfirmationNumber,
                contentDescription = null,
                tint = PureWhite,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Entradas gratuitas hasta completar aforo",
                color = PureWhite,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.04.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .background(PureWhite.copy(alpha = 0.22f), RoundedCornerShape(3.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = "TAQUILLA DIARIA",
                color = PureWhite,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FestivalBottomNav(
    currentTab: FestivalNavTab,
    savedCount: Int,
    onTabSelected: (FestivalNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.97f),
        tonalElevation = 6.dp,
        shadowElevation = 8.dp
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.height(64.dp)
        ) {
            val navItems = listOf(
                Triple(FestivalNavTab.INICIO, "Inicio", Icons.Default.Theaters),
                Triple(FestivalNavTab.AGENDA, "Agenda", Icons.Default.CalendarMonth),
                Triple(FestivalNavTab.SECCIONES, "Secciones", Icons.Default.MovieFilter),
                Triple(FestivalNavTab.NOTICIAS, "Noticias", Icons.Default.Newspaper),
                Triple(FestivalNavTab.MI_RUTA, "Mi Ruta", Icons.Default.Bookmark)
            )

            navItems.forEach { (tab, label, icon) ->
                val selected = currentTab == tab
                NavigationBarItem(
                    selected = selected,
                    onClick = { onTabSelected(tab) },
                    icon = {
                        if (tab == FestivalNavTab.MI_RUTA && savedCount > 0) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = BrandRed,
                                        contentColor = PureWhite
                                    ) {
                                        Text(text = savedCount.toString(), fontSize = 9.sp)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        } else {
                            Icon(
                                imageVector = icon,
                                contentDescription = label,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = label,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PureWhite,
                        selectedTextColor = PrimaryRed,
                        indicatorColor = PrimaryRed,
                        unselectedIconColor = FilmGray,
                        unselectedTextColor = FilmGray
                    ),
                    modifier = Modifier.testTag("tab_${label.lowercase()}")
                )
            }
        }
    }
}

@Composable
fun EventItemCard(
    event: FestivalEvent,
    onEventClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onEventClick() }
            .testTag("event_card_${event.id}"),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceContainerLowest
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header: Time pill, category tag, and favorite bookmark
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // Time pill
                    Box(
                        modifier = Modifier
                            .background(FilmBlack, RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = event.time,
                            color = PureWhite,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Category badge
                    Box(
                        modifier = Modifier
                            .background(
                                when (event.categoryKey) {
                                    "jica" -> TertiaryContainer
                                    "oficial" -> PrimaryRed
                                    "concierto" -> FilmCoal
                                    else -> SurfaceContainerHigh
                                },
                                RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = event.category.uppercase(),
                            color = when (event.categoryKey) {
                                "jica", "oficial", "concierto" -> PureWhite
                                else -> MaterialTheme.colorScheme.onSurface
                            },
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Duration pill
                    Text(
                        text = event.duration,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        color = FilmGray
                    )
                }

                // Bookmark icon button
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.size(34.dp)
                ) {
                    Icon(
                        imageVector = if (event.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = if (event.isFavorite) "Quitar de Mi Ruta" else "Añadir a Mi Ruta",
                        tint = if (event.isFavorite) BrandRed else FilmGray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Thumbnail layout if available
            if (event.imageUrl != null) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = event.imageUrl,
                        contentDescription = event.title,
                        modifier = Modifier
                            .size(width = 72.dp, height = 90.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(FilmCoal),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = event.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 19.sp
                        )
                        Text(
                            text = event.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            } else {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )
            }

            // Footer: Venue & Tag
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = BrandRed,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = event.venue,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Box(
                    modifier = Modifier
                        .background(PaperTint, RoundedCornerShape(3.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = event.tag.uppercase(),
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        color = FilmCoal,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun EventDetailDialog(
    event: FestivalEvent,
    onDismiss: () -> Unit,
    onToggleFavorite: () -> Unit,
    onOpenVenues: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onToggleFavorite()
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (event.isFavorite) FilmCoal else BrandRed
                )
            ) {
                Icon(
                    imageVector = if (event.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (event.isFavorite) "En Mi Ruta (Quitar)" else "Añadir a Mi Ruta",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar", color = FilmGray)
            }
        },
        title = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(BrandRed, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = event.category.uppercase(),
                            color = PureWhite,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "${event.dayLabel} • ${event.time}",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = FilmGray
                    )
                }
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (event.imageUrl != null) {
                    AsyncImage(
                        model = event.imageUrl,
                        contentDescription = event.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = event.description,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )

                // Venue info box
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = PaperBase,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = BrandRed,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = event.venue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            text = event.venueAddress,
                            fontSize = 11.sp,
                            color = FilmGray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Duración: ${event.duration}",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Acceso: ${event.tag}",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = FilmGray
                    )
                }
            }
        }
    )
}

@Composable
fun NewsDetailDialog(
    news: FestivalNews,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = BrandRed)
            ) {
                Text("Entendido", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            }
        },
        title = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(BrandRed, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = news.tag.uppercase(),
                            color = PureWhite,
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = news.date,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = FilmGray
                    )
                }
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                AsyncImage(
                    model = news.imageUrl,
                    contentDescription = news.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = news.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = news.content,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )
            }
        }
    )
}

@Composable
fun TicketInfoDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = BrandRed)
            ) {
                Text("Cerrar", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(BrandRed, RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ConfirmationNumber,
                        contentDescription = null,
                        tint = PureWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column {
                    Text(
                        text = "ENTRADAS & TAQUILLA",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Acceso Gratuito • 25º Aniversario",
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        color = BrandRed
                    )
                }
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = PaperBase,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "📍 Taquilla Central: Casa Municipal de Cultura",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Plaza Domingo Álvarez Acebal, 2",
                            fontSize = 11.sp,
                            color = FilmGray
                        )
                        Text(
                            text = "• Horario: Abierta cada día a partir de las 16:30 H.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "• Condiciones: Máximo 2 entradas por persona.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "• Precio: 0,00 € (Todas las sesiones son gratuitas hasta completar aforo).",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandRed
                        )
                    }
                }

                Text(
                    text = "Recomendamos acudir con antelación a las sesiones de las 18:00 y 20:00 H de Sección Oficial y Ciclo Isabel Coixet para garantizar butaca.",
                    style = MaterialTheme.typography.bodySmall,
                    color = FilmGray
                )
            }
        }
    )
}

@Composable
fun PdfProgramDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = BrandRed)
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Descargar Guía Oficial", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Volver", color = FilmGray)
            }
        },
        title = {
            Text(
                text = "CUADERNO DE PROGRAMACIÓN 2026",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "El programa oficial en formato PDF incluye la guía completa del 25º Certamen:",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "• Fichas artísticas y sinopsis de los 33 cortometrajes a concurso.\n• Cuadrícula horaria de proyecciones y actividades JICA.\n• Semblanzas de Isabel Coixet y Pelayo Gutiérrez.\n• Mapa de sedes y puntos de encuentro festivaleros.",
                    style = MaterialTheme.typography.bodySmall,
                    color = FilmGray,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp
                )
                Text(
                    text = "Disponible gratuitamente para todos los asistentes.",
                    fontSize = 11.sp,
                    color = BrandRed,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@Composable
fun VenuesSheet(
    venues: List<FestivalVenue>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = BrandRed)
            ) {
                Text("Cerrar", fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Storefront,
                    contentDescription = null,
                    tint = BrandRed
                )
                Text(
                    text = "SEDES DEL FESTIVAL",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                venues.forEach { venue ->
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = PaperBase,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = venue.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = venue.price,
                                    fontSize = 9.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = BrandRed,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = venue.role,
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = venue.address,
                                fontSize = 10.sp,
                                color = FilmGray
                            )
                            Text(
                                text = venue.schedule,
                                fontSize = 10.sp,
                                color = FilmCoal
                            )
                        }
                    }
                }
            }
        }
    )
}
