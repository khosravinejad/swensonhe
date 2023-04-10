package com.morteza.swensonhe.domain.usecase

import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.repository.LocationRepository
import javax.inject.Inject

class SaveLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseUseCase<Location, SaveLocationUseCase.Result> {

    override suspend fun invoke(params: Location): Result {
        return try {
            locationRepository.saveLocation(params)
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

    // We can define different types of error here -> e.g. APIError, DatabaseError, NetworkError and etc.
    sealed class Result {
        object Success : Result()
        data class Error(val message: String?, val throwable: Throwable) : Result()
    }
}