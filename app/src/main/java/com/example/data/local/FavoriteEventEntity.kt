package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_events")
data class FavoriteEventEntity(
    @PrimaryKey
    val eventId: String,
    val savedAtTimestamp: Long = System.currentTimeMillis()
)
