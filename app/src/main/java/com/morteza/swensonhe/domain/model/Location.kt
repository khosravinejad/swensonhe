package com.morteza.swensonhe.domain.model

data class Location(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val url: String
) {
    val fullRegionName = "$name, $region, $country"
}
