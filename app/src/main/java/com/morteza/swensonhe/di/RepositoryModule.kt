package com.morteza.swensonhe.di

import com.morteza.swensonhe.data.repository.ForecastRepositoryImpl
import com.morteza.swensonhe.data.repository.LocationRepositoryImpl
import com.morteza.swensonhe.data.source.local.SwensonHeDatabase
import com.morteza.swensonhe.data.source.remote.ApiServices
import com.morteza.swensonhe.domain.repository.ForecastRepository
import com.morteza.swensonhe.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLocationRepository(
        apiServices: ApiServices,
        database: SwensonHeDatabase
    ): LocationRepository = LocationRepositoryImpl(apiServices, database)

    @Singleton
    @Provides
    fun provideForecastRepository(apiServices: ApiServices): ForecastRepository =
        ForecastRepositoryImpl(apiServices)
}