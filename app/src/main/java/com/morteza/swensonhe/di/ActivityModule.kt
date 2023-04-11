package com.morteza.swensonhe.di

import com.morteza.swensonhe.presentation.forecast.recyclerview.ForecastAdapter
import com.morteza.swensonhe.presentation.search.recyclerview.SearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideSearchAdapter(): SearchAdapter = SearchAdapter()

    @Provides
    fun provideForecastAdapter(): ForecastAdapter = ForecastAdapter()
}