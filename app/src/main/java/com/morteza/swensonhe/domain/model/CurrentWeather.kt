package com.morteza.swensonhe.domain.model

data class CurrentWeather(
    val lastUpdatedTime: String,
    val tempC: Float,
    val tempF: Float,
    val conditionText: String,
    val conditionIconUrl: String,
    val conditionCode: Int,
    val windMph: Float,
    val windKph: Float,
    val windDegree: Int,
    val windDirection: String,
    val humidity: Int,
    val feelsLikeC: Float,
    val feelsLikeF: Float,
    val isDay: Int
) {
    val httpUrl = "https:$conditionIconUrl"
}