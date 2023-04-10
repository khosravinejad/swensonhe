package com.morteza.swensonhe.domain.usecase

import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoredLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseUseCase<Unit, GetStoredLocationsUseCase.Result> {

    override suspend fun invoke(params: Unit): Result {
        return try {
            Result.Success(locationRepository.getSavedLocations())
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

    // We can define different types of error here -> e.g. APIError, DatabaseError, NetworkError and etc.
    sealed class Result {
        data class Success(val data: Flow<List<Location>>) : Result()
        data class Error(val message: String?, val throwable: Throwable) : Result()
    }
}