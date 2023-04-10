package com.morteza.swensonhe.domain.model

data class FullForecast(
    val currentWeather: CurrentWeather,
    val forecast: List<Forecast>
)