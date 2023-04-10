package com.morteza.swensonhe.domain.repository

import com.morteza.swensonhe.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun searchLocation(query: String): List<Location>
    suspend fun saveLocation(location: Location)
    suspend fun removeLocation(locationId: Int)
    fun getSavedLocations(): Flow<List<Location>>
    suspend fun getLocationById(locationId: Int): Location
}