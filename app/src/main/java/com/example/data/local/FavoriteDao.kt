package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT eventId FROM favorite_events")
    fun getAllFavoriteIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEventEntity)

    @Query("DELETE FROM favorite_events WHERE eventId = :eventId")
    suspend fun removeFavorite(eventId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_events WHERE eventId = :eventId)")
    suspend fun isFavorite(eventId: String): Boolean
}
