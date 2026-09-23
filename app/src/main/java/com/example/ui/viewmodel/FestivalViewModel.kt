package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.model.EventTimeCategory
import com.example.data.model.FestivalDay
import com.example.data.model.FestivalEvent
import com.example.data.model.FestivalNews
import com.example.data.model.FestivalSection
import com.example.data.model.FestivalVenue
import com.example.data.repository.FestivalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class FestivalNavTab {
    INICIO,
    AGENDA,
    SECCIONES,
    NOTICIAS,
    MI_RUTA
}

class FestivalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FestivalRepository

    init {
        val db = AppDatabase.getInstance(application)
        repository = FestivalRepository(db.favoriteDao())
    }

    val festivalDays: List<FestivalDay> = repository.festivalDays
    val sections: List<FestivalSection> = repository.sections
    val newsList: List<FestivalNews> = repository.newsList
    val venues: List<FestivalVenue> = repository.venues

    private val _currentTab = MutableStateFlow(FestivalNavTab.INICIO)
    val currentTab: StateFlow<FestivalNavTab> = _currentTab.asStateFlow()

    private val _selectedAgendaDayId = MutableStateFlow("sab10")
    val selectedAgendaDayId: StateFlow<String> = _selectedAgendaDayId.asStateFlow()

    private val _selectedTimeCategory = MutableStateFlow(EventTimeCategory.ALL)
    val selectedTimeCategory: StateFlow<EventTimeCategory> = _selectedTimeCategory.asStateFlow()

    private val _selectedAgendaCategoryKey = MutableStateFlow("todos")
    val selectedAgendaCategoryKey: StateFlow<String> = _selectedAgendaCategoryKey.asStateFlow()

    private val _sectionsFilterKey = MutableStateFlow("todas")
    val sectionsFilterKey: StateFlow<String> = _sectionsFilterKey.asStateFlow()

    private val _sectionsSearchQuery = MutableStateFlow("")
    val sectionsSearchQuery: StateFlow<String> = _sectionsSearchQuery.asStateFlow()

    private val _selectedEvent = MutableStateFlow<FestivalEvent?>(null)
    val selectedEvent: StateFlow<FestivalEvent?> = _selectedEvent.asStateFlow()

    private val _selectedNews = MutableStateFlow<FestivalNews?>(null)
    val selectedNews: StateFlow<FestivalNews?> = _selectedNews.asStateFlow()

    private val _showTicketModal = MutableStateFlow(false)
    val showTicketModal: StateFlow<Boolean> = _showTicketModal.asStateFlow()

    private val _showPdfModal = MutableStateFlow(false)
    val showPdfModal: StateFlow<Boolean> = _showPdfModal.asStateFlow()

    private val _showVenuesModal = MutableStateFlow(false)
    val showVenuesModal: StateFlow<Boolean> = _showVenuesModal.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    // All events with dynamic reactive favorite flags from Room
    val allEvents: StateFlow<List<FestivalEvent>> = repository.eventsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Events filtered for the Agenda screen
    val filteredAgendaEvents: StateFlow<List<FestivalEvent>> = combine(
        allEvents,
        _selectedAgendaDayId,
        _selectedTimeCategory,
        _selectedAgendaCategoryKey
    ) { events, dayId, timeCat, catKey ->
        events.filter { event ->
            val matchDay = event.dayId == dayId
            val matchTime = when (timeCat) {
                EventTimeCategory.ALL -> true
                else -> event.timeCategory == timeCat
            }
            val matchCat = when (catKey) {
                "todos" -> true
                else -> event.categoryKey.contains(catKey, ignoreCase = true)
            }
            matchDay && matchTime && matchCat
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Bookmarked user itinerary
    val favoriteEvents: StateFlow<List<FestivalEvent>> = allEvents.combine(_currentTab) { events, _ ->
        events.filter { it.isFavorite }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Sections filtered by chip & query
    val filteredSections: StateFlow<List<FestivalSection>> = combine(
        _sectionsFilterKey,
        _sectionsSearchQuery
    ) { key, query ->
        val q = query.trim().lowercase()
        sections.filter { section ->
            val matchesCategory = key == "todas" || section.key == key
            val matchesQuery = q.isEmpty() ||
                    section.title.lowercase().contains(q) ||
                    section.subtitle.lowercase().contains(q) ||
                    section.description.lowercase().contains(q) ||
                    section.keywords.lowercase().contains(q)
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), sections)

    fun selectTab(tab: FestivalNavTab) {
        _currentTab.value = tab
    }

    fun selectAgendaDay(dayId: String) {
        _selectedAgendaDayId.value = dayId
    }

    fun selectTimeCategory(category: EventTimeCategory) {
        _selectedTimeCategory.value = category
    }

    fun selectAgendaCategory(categoryKey: String) {
        _selectedAgendaCategoryKey.value = categoryKey
    }

    fun setSectionsFilter(key: String) {
        _sectionsFilterKey.value = key
    }

    fun setSectionsSearchQuery(query: String) {
        _sectionsSearchQuery.value = query
    }

    fun toggleFavorite(event: FestivalEvent) {
        viewModelScope.launch {
            val willBeFavorite = !event.isFavorite
            repository.toggleFavorite(event.id, event.isFavorite)
            _snackbarMessage.value = if (willBeFavorite) {
                "★ Añadido a Mi Ruta: ${event.title}"
            } else {
                "Eliminado de Mi Ruta"
            }
        }
    }

    fun openEventDetails(event: FestivalEvent) {
        _selectedEvent.value = event
    }

    fun closeEventDetails() {
        _selectedEvent.value = null
    }

    fun openNewsDetails(news: FestivalNews) {
        _selectedNews.value = news
    }

    fun closeNewsDetails() {
        _selectedNews.value = null
    }

    fun openTicketModal() {
        _showTicketModal.value = true
    }

    fun closeTicketModal() {
        _showTicketModal.value = false
    }

    fun openPdfModal() {
        _showPdfModal.value = true
    }

    fun closePdfModal() {
        _showPdfModal.value = false
    }

    fun openVenuesModal() {
        _showVenuesModal.value = true
    }

    fun closeVenuesModal() {
        _showVenuesModal.value = false
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }
}
