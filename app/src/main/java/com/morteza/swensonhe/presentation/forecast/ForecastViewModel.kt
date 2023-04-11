package com.morteza.swensonhe.presentation.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.morteza.swensonhe.domain.model.FullForecast
import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.usecase.GetForecastUseCase
import com.morteza.swensonhe.domain.usecase.GetLocationByIdUseCase
import com.morteza.swensonhe.presentation.base.BaseViewModel
import com.morteza.swensonhe.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getForecastUseCase: GetForecastUseCase,
    private val getLocationByIdUseCase: GetLocationByIdUseCase
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO handle coroutine error
    }

    private val _location: MutableLiveData<Location> = MutableLiveData()
    val location: LiveData<Location> = _location

    private val _fullForecast: MutableLiveData<FullForecast> = MutableLiveData()
    val fullForecast: LiveData<FullForecast> = _fullForecast

    fun getForecastsByLocation(locationId: Int) {
        launchCoroutineIO {
            when (val result = getLocationByIdUseCase.invoke(locationId)) {
                is GetLocationByIdUseCase.Result.Success -> {
                    _location.postValue(result.data)
                    getForecast(result.data)
                }
                is GetLocationByIdUseCase.Result.Error -> {
                    // TODO error handling
                }
            }
        }
    }

    private fun getForecast(location: Location) {
        launchCoroutineIO {
            when (val result = getForecastUseCase.invoke(
                GetForecastUseCase.ForecastUseCaseParams(location.lat, location.lon)
            )) {
                is GetForecastUseCase.Result.Success -> {
                    _fullForecast.postValue(result.data)
                }
                is GetForecastUseCase.Result.Error -> {
                    // TODO error handling
                }
            }
        }
    }
}