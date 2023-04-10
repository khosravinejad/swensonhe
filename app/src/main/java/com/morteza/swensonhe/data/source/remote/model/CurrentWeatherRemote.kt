package com.morteza.swensonhe.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherRemote(
    @SerializedName("last_updated") val lastUpdatedDate: String,
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("temp_f") val tempF: Float,
    @SerializedName("feelslike_c") val feelsLikeC: Float,
    @SerializedName("feelslike_f") val feelsLikeF: Float,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("wind_mph") val windMph: Float,
    @SerializedName("wind_kph") val windKph: Float,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("condition") val condition: ConditionRemote
)