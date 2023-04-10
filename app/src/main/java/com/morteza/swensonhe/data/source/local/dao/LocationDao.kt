package com.morteza.swensonhe.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.morteza.swensonhe.data.source.local.model.LocationCache
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<LocationCache>)

    @Query("SELECT * FROM locations ORDER BY timestamp DESC")
    fun getAllLocation(): Flow<List<LocationCache>>

    @Query("DELETE FROM locations WHERE id = :id")
    suspend fun deleteLocation(id: Int)

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocation(id: Int): LocationCache

    @Query("DELETE FROM locations")
    suspend fun deleteAll()
}