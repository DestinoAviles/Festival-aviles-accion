package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.EventDetailDialog
import com.example.ui.components.FestivalBottomNav
import com.example.ui.components.FestivalNoticeBanner
import com.example.ui.components.FestivalTopHeader
import com.example.ui.components.NewsDetailDialog
import com.example.ui.components.PdfProgramDialog
import com.example.ui.components.TicketInfoDialog
import com.example.ui.components.VenuesSheet
import com.example.ui.screens.AgendaScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MyRouteScreen
import com.example.ui.screens.NewsScreen
import com.example.ui.screens.SectionsScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.FestivalNavTab
import com.example.ui.viewmodel.FestivalViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                FestivalApp()
            }
        }
    }
}

@Composable
fun FestivalApp(
    viewModel: FestivalViewModel = viewModel()
) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val selectedAgendaDayId by viewModel.selectedAgendaDayId.collectAsStateWithLifecycle()
    val selectedTimeCategory by viewModel.selectedTimeCategory.collectAsStateWithLifecycle()
    val selectedAgendaCategoryKey by viewModel.selectedAgendaCategoryKey.collectAsStateWithLifecycle()

    val sectionsFilterKey by viewModel.sectionsFilterKey.collectAsStateWithLifecycle()
    val sectionsSearchQuery by viewModel.sectionsSearchQuery.collectAsStateWithLifecycle()

    val selectedEvent by viewModel.selectedEvent.collectAsStateWithLifecycle()
    val selectedNews by viewModel.selectedNews.collectAsStateWithLifecycle()
    val showTicketModal by viewModel.showTicketModal.collectAsStateWithLifecycle()
    val showPdfModal by viewModel.showPdfModal.collectAsStateWithLifecycle()
    val showVenuesModal by viewModel.showVenuesModal.collectAsStateWithLifecycle()
    val snackbarMessage by viewModel.snackbarMessage.collectAsStateWithLifecycle()

    val agendaEvents by viewModel.filteredAgendaEvents.collectAsStateWithLifecycle()
    val favoriteEvents by viewModel.favoriteEvents.collectAsStateWithLifecycle()
    val filteredSections by viewModel.filteredSections.collectAsStateWithLifecycle()

    // For Home screen agenda mini-preview: events for selected day
    val allEvents by viewModel.allEvents.collectAsStateWithLifecycle()
    val homeDayEvents = remember(allEvents, selectedAgendaDayId) {
        allEvents.filter { it.dayId == selectedAgendaDayId }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { msg ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = msg,
                    duration = SnackbarDuration.Short
                )
                viewModel.clearSnackbar()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Column {
                FestivalTopHeader(
                    currentTab = currentTab,
                    savedCount = favoriteEvents.size,
                    onTabSelected = { viewModel.selectTab(it) },
                    onOpenTickets = { viewModel.openTicketModal() },
                    onOpenVenues = { viewModel.openVenuesModal() }
                )
                FestivalNoticeBanner(
                    onClick = { viewModel.openTicketModal() }
                )
            }
        },
        bottomBar = {
            FestivalBottomNav(
                currentTab = currentTab,
                savedCount = favoriteEvents.size,
                onTabSelected = { viewModel.selectTab(it) }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(
                targetState = currentTab,
                label = "ScreenTransition"
            ) { tab ->
                when (tab) {
                    FestivalNavTab.INICIO -> {
                        HomeScreen(
                            days = viewModel.festivalDays,
                            selectedDayId = selectedAgendaDayId,
                            onSelectDay = { viewModel.selectAgendaDay(it) },
                            dayEvents = homeDayEvents,
                            sections = viewModel.sections,
                            newsList = viewModel.newsList,
                            onEventClick = { viewModel.openEventDetails(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) },
                            onNavigateTab = { viewModel.selectTab(it) },
                            onNewsClick = { viewModel.openNewsDetails(it) },
                            onOpenPdfModal = { viewModel.openPdfModal() },
                            onOpenTicketModal = { viewModel.openTicketModal() },
                            onOpenVenuesModal = { viewModel.openVenuesModal() }
                        )
                    }

                    FestivalNavTab.AGENDA -> {
                        AgendaScreen(
                            days = viewModel.festivalDays,
                            selectedDayId = selectedAgendaDayId,
                            onSelectDay = { viewModel.selectAgendaDay(it) },
                            events = agendaEvents,
                            venues = viewModel.venues,
                            selectedTimeCategory = selectedTimeCategory,
                            onSelectTimeCategory = { viewModel.selectTimeCategory(it) },
                            selectedCategoryKey = selectedAgendaCategoryKey,
                            onSelectCategoryKey = { viewModel.selectAgendaCategory(it) },
                            onEventClick = { viewModel.openEventDetails(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) },
                            savedFavoritesCount = favoriteEvents.size,
                            onNavigateToRoute = { viewModel.selectTab(FestivalNavTab.MI_RUTA) },
                            onOpenTicketInfo = { viewModel.openTicketModal() },
                            onOpenVenuesInfo = { viewModel.openVenuesModal() }
                        )
                    }

                    FestivalNavTab.SECCIONES -> {
                        SectionsScreen(
                            sections = filteredSections,
                            selectedFilterKey = sectionsFilterKey,
                            onSelectFilterKey = { viewModel.setSectionsFilter(it) },
                            searchQuery = sectionsSearchQuery,
                            onSearchQueryChange = { viewModel.setSectionsSearchQuery(it) },
                            onExploreSectionInAgenda = { categoryKey ->
                                viewModel.selectAgendaCategory(categoryKey)
                                viewModel.selectTab(FestivalNavTab.AGENDA)
                            },
                            onOpenPdfModal = { viewModel.openPdfModal() }
                        )
                    }

                    FestivalNavTab.NOTICIAS -> {
                        NewsScreen(
                            newsList = viewModel.newsList,
                            onNewsClick = { viewModel.openNewsDetails(it) }
                        )
                    }

                    FestivalNavTab.MI_RUTA -> {
                        MyRouteScreen(
                            favoriteEvents = favoriteEvents,
                            onEventClick = { viewModel.openEventDetails(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) },
                            onNavigateToAgenda = { viewModel.selectTab(FestivalNavTab.AGENDA) },
                            onOpenTicketModal = { viewModel.openTicketModal() }
                        )
                    }
                }
            }

            // Dialogs
            selectedEvent?.let { event ->
                EventDetailDialog(
                    event = event,
                    onDismiss = { viewModel.closeEventDetails() },
                    onToggleFavorite = { viewModel.toggleFavorite(event) },
                    onOpenVenues = {
                        viewModel.closeEventDetails()
                        viewModel.openVenuesModal()
                    }
                )
            }

            selectedNews?.let { news ->
                NewsDetailDialog(
                    news = news,
                    onDismiss = { viewModel.closeNewsDetails() }
                )
            }

            if (showTicketModal) {
                TicketInfoDialog(
                    onDismiss = { viewModel.closeTicketModal() }
                )
            }

            if (showPdfModal) {
                PdfProgramDialog(
                    onDismiss = { viewModel.closePdfModal() }
                )
            }

            if (showVenuesModal) {
                VenuesSheet(
                    venues = viewModel.venues,
                    onDismiss = { viewModel.closeVenuesModal() }
                )
            }
        }
    }
}
