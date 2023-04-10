package com.morteza.swensonhe.domain.usecase

import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.repository.LocationRepository
import javax.inject.Inject

class SearchLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseUseCase<String, SearchLocationUseCase.Result> {

    override suspend fun invoke(params: String): Result {
        return try {
            Result.Success(locationRepository.searchLocation(params))
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

    // We can define different types of error here -> e.g. APIError, DatabaseError, NetworkError and etc.
    sealed class Result {
        data class Success(val data: List<Location>) : Result()
        data class Error(val message: String?, val throwable: Throwable) : Result()
    }
}