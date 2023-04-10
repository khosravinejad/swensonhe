package com.morteza.swensonhe.domain.usecase

import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationByIdUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseUseCase<Int, GetLocationByIdUseCase.Result> {

    override suspend fun invoke(params: Int): Result {
        return try {
            Result.Success(locationRepository.getLocationById(params))
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

    // We can define different types of error here -> e.g. APIError, DatabaseError, NetworkError and etc.
    sealed class Result {
        data class Success(val data: Location) : Result()
        data class Error(val message: String?, val throwable: Throwable) : Result()
    }
}