package com.morteza.swensonhe.data.repository

import com.morteza.swensonhe.data.source.local.SwensonHeDatabase
import com.morteza.swensonhe.data.source.local.model.LocationCache
import com.morteza.swensonhe.data.source.remote.ApiServices
import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.repository.LocationRepository
import com.morteza.swensonhe.exception.ServerException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val database: SwensonHeDatabase
) : LocationRepository {
    override suspend fun searchLocation(query: String): List<Location> {
        val response = apiServices.searchLocation(ApiServices.API_KEY, query)
        val searchResult = response.body()
        if (response.isSuccessful && searchResult != null) {
            return searchResult.map {
                Location(
                    id = it.id,
                    name = it.name,
                    region = it.region,
                    country = it.country,
                    lat = it.latitude,
                    lon = it.longitude,
                    url = it.url
                )
            }
        } else {
            throw ServerException(response.code(), response.message())
        }
    }

    override suspend fun saveLocation(location: Location) {
        database.locationDao.insert(
            LocationCache(
                id = location.id,
                name = location.name,
                region = location.region,
                country = location.country,
                lat = location.lat,
                lon = location.lon,
                url = location.url,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun removeLocation(locationId: Int) {
        database.locationDao.deleteLocation(locationId)
    }

    override fun getSavedLocations(): Flow<List<Location>> {
        val locationCaches = database.locationDao.getAllLocation()
        return locationCaches.map { list ->
            list.map {
                Location(
                    id = it.id,
                    name = it.name,
                    region = it.region,
                    country = it.country,
                    lat = it.lat,
                    lon = it.lon,
                    url = it.url
                )
            }
        }
    }

    override suspend fun getLocationById(locationId: Int): Location {
        val locationCache = database.locationDao.getLocation(locationId)
        return Location(
            id = locationCache.id,
            name = locationCache.name,
            region = locationCache.region,
            country = locationCache.country,
            lat = locationCache.lat,
            lon = locationCache.lon,
            url = locationCache.url
        )
    }
}