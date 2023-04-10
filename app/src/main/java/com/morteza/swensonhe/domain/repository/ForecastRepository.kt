package com.morteza.swensonhe.domain.repository

import com.morteza.swensonhe.domain.model.FullForecast

interface ForecastRepository {
    suspend fun getForecast(lat: Float, lon: Float, daysNumber: Int): FullForecast
}