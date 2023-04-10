package com.morteza.swensonhe.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationCache(
    @PrimaryKey val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val url: String,
    val timestamp: Long
)