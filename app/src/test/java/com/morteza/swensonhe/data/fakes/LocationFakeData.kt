package com.morteza.swensonhe.data.fakes

import com.morteza.swensonhe.domain.model.Location

object LocationFakeData {
    fun getFakeLocation(locationId: Int) = Location(
        id = locationId,
        name = "Istanbul",
        region = "Istanbul",
        country = "Turkey",
        lat = 41.0082f,
        lon = 28.9784f,
        url = "istanbul_istanbul_turkey"
    )
}