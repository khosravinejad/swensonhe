package com.morteza.swensonhe.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ConditionRemote(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val iconUrl: String,
    @SerializedName("code") val code: Int
)