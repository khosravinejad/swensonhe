package com.morteza.swensonhe.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastAstrologyDetailsRemote(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("moonrise") val moonrise: String,
    @SerializedName("moonset") val moonset: String
)
