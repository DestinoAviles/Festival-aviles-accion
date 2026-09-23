package com.example.data.model

enum class EventTimeCategory {
    ALL,
    MORNING,    // 10:00 - 14:00
    AFTERNOON,  // 17:00 - 20:00
    NIGHT       // 20:00+
}

data class FestivalEvent(
    val id: String,
    val dayId: String,
    val dayLabel: String,
    val time: String,
    val timeCategory: EventTimeCategory,
    val category: String,
    val categoryKey: String,
    val title: String,
    val description: String,
    val venue: String,
    val venueAddress: String = "Avilés, Asturias",
    val duration: String,
    val tag: String,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false
)

data class FestivalDay(
    val id: String,
    val dayShort: String,
    val dayNumber: String,
    val dateTitle: String,
    val dayIndex: Int
)

data class FestivalSection(
    val id: String,
    val key: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val dateVenue: String,
    val worksCount: String,
    val tag: String,
    val imageUrl: String,
    val keywords: String
)

data class FestivalNews(
    val id: String,
    val date: String,
    val tag: String,
    val title: String,
    val summary: String,
    val content: String,
    val imageUrl: String
)

data class FestivalVenue(
    val id: String,
    val name: String,
    val role: String,
    val address: String,
    val schedule: String,
    val price: String = "0,00 € (Gratis hasta aforo)"
)
