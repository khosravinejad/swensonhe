package com.morteza.swensonhe.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastRemote(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Long,
    @SerializedName("day") val day: ForecastDayDetailsDataModel,
    @SerializedName("astro") val astrology: ForecastAstrologyDetailsRemote
)
