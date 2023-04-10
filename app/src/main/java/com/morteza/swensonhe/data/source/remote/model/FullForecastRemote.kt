package com.morteza.swensonhe.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class FullForecastRemote(
    @SerializedName("current") val currentWeather: CurrentWeatherRemote,
    @SerializedName("forecast") val forecast: ForecastDayRemote
)

data class ForecastDayRemote(
    @SerializedName("forecastday") val forecastDays: List<ForecastRemote>
)
