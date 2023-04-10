package com.morteza.swensonhe.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastDayDetailsDataModel(
    @SerializedName("maxtemp_c") val maxTempC: Float,
    @SerializedName("maxtemp_f") val maxTempF: Float,
    @SerializedName("mintemp_c") val minTempC: Float,
    @SerializedName("mintemp_F") val minTempF: Float,
    @SerializedName("daily_chance_of_rain") val chanceOfRain: Int,
    @SerializedName("daily_chance_of_snow") val chanceOfSnow: Int,
    @SerializedName("condition") val condition: ConditionRemote,
)
