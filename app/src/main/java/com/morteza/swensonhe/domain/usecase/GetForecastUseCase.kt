package com.morteza.swensonhe.domain.usecase

import com.morteza.swensonhe.domain.model.FullForecast
import com.morteza.swensonhe.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) : BaseUseCase<GetForecastUseCase.ForecastUseCaseParams, GetForecastUseCase.Result> {

    override suspend fun invoke(params: ForecastUseCaseParams): Result {
        return try {
            Result.Success(forecastRepository.getForecast(params.lat, params.lon, 10))
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

    data class ForecastUseCaseParams(
        val lat: Float,
        val lon: Float
    )

    // We can define different types of error here -> e.g. APIError, DatabaseError, NetworkError and etc.
    sealed class Result {
        data class Success(val data: FullForecast) : Result()
        data class Error(val message: String?, val throwable: Throwable) : Result()
    }
}