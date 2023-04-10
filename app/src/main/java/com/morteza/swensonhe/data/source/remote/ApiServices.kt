package com.morteza.swensonhe.data.source.remote

import com.morteza.swensonhe.data.source.remote.model.FullForecastRemote
import com.morteza.swensonhe.data.source.remote.model.LocationRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
        const val API_KEY = "2c34c696f5b84f9882f91806232303"
    }

    @GET("forecast.json")
    suspend fun getForecastByLocation(
        @Query("key") key: String,
        @Query("q") latLon: String,
        @Query("days") forecastDaysNumber: Int
    ): Response<FullForecastRemote>

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") key: String,
        @Query("q") query: String,
    ): Response<List<LocationRemote>>
}