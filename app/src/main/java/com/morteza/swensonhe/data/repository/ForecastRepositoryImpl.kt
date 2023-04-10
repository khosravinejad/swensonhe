package com.morteza.swensonhe.data.repository

import com.morteza.swensonhe.data.source.remote.ApiServices
import com.morteza.swensonhe.domain.model.CurrentWeather
import com.morteza.swensonhe.domain.model.Forecast
import com.morteza.swensonhe.domain.model.FullForecast
import com.morteza.swensonhe.domain.repository.ForecastRepository
import com.morteza.swensonhe.exception.ServerException
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
) : ForecastRepository {
    override suspend fun getForecast(
        lat: Float,
        lon: Float,
        daysNumber: Int
    ): FullForecast {
        val response =
            apiServices.getForecastByLocation(ApiServices.API_KEY, "$lat $lon", daysNumber)
        val forecast = response.body()
        if (response.isSuccessful && forecast != null) {
            val currentWeatherResponse = forecast.currentWeather
            val currentWeather = CurrentWeather(
                lastUpdatedTime = currentWeatherResponse.lastUpdatedDate,
                tempC = currentWeatherResponse.tempC,
                tempF = currentWeatherResponse.tempF,
                conditionText = currentWeatherResponse.condition.text,
                conditionIconUrl = currentWeatherResponse.condition.iconUrl,
                conditionCode = currentWeatherResponse.condition.code,
                windMph = currentWeatherResponse.windMph,
                windKph = currentWeatherResponse.windKph,
                windDegree = currentWeatherResponse.windDegree,
                windDirection = currentWeatherResponse.windDirection,
                humidity = currentWeatherResponse.humidity,
                feelsLikeC = currentWeatherResponse.feelsLikeC,
                feelsLikeF = currentWeatherResponse.feelsLikeF,
                isDay = currentWeatherResponse.isDay
            )
            val forecastDaysResponse = forecast.forecast.forecastDays
            val forecasts = forecastDaysResponse.map {
                Forecast(
                    date = it.date,
                    dateEpoch = it.dateEpoch,
                    maxTempC = it.day.maxTempC,
                    maxTempF = it.day.maxTempF,
                    minTempC = it.day.minTempC,
                    minTempF = it.day.minTempF,
                    chanceOfRain = it.day.chanceOfRain,
                    chanceOfSnow = it.day.chanceOfSnow,
                    conditionText = it.day.condition.text,
                    conditionIconUrl = it.day.condition.iconUrl,
                    conditionCode = it.day.condition.code,
                    sunriseTime = it.astrology.sunrise,
                    sunsetTime = it.astrology.sunset,
                    moonriseTime = it.astrology.moonrise,
                    moonSetTime = it.astrology.moonset
                )
            }
            return FullForecast(currentWeather, forecasts)
        } else {
            throw ServerException(response.code(), response.message())
        }
    }

}