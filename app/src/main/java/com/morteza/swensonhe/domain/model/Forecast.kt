package com.morteza.swensonhe.domain.model

import java.io.Serializable

data class Forecast(
    val date: String,
    val dateEpoch: Long,
    val maxTempC: Float,
    val maxTempF: Float,
    val minTempC: Float,
    val minTempF: Float,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val conditionText: String,
    val conditionIconUrl: String,
    val conditionCode: Int,
    val sunriseTime: String,
    val sunsetTime: String,
    val moonriseTime: String,
    val moonSetTime: String
) : Serializable {
    val dateInMilliSecond = dateEpoch * 1000
    val httpUrl = "https:$conditionIconUrl"
}