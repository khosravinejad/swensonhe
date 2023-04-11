package com.morteza.swensonhe.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.usecase.GetStoredLocationsUseCase
import com.morteza.swensonhe.domain.usecase.SaveLocationUseCase
import com.morteza.swensonhe.presentation.base.BaseViewModel
import com.morteza.swensonhe.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getStoredLocationsUseCase: GetStoredLocationsUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) :
    BaseViewModel(contextProvider) {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO handle coroutine error
    }

    private val _storedLocations: MutableLiveData<List<Location>> = MutableLiveData()
    val storedLocations: LiveData<List<Location>> = _storedLocations

    fun getStoredLocations() {
        launchCoroutineIO {
            when (val result = getStoredLocationsUseCase.invoke(Unit)) {
                is GetStoredLocationsUseCase.Result.Success -> {
                    result.data.collect {
                        _storedLocations.postValue(it)
                    }
                }
                is GetStoredLocationsUseCase.Result.Error -> {
                    // TODO error handling
                }
            }
        }
    }

    fun saveLocation(location: Location) {
        launchCoroutineIO {
            when (val result = saveLocationUseCase.invoke(location)) {
                is SaveLocationUseCase.Result.Success -> {}
                is SaveLocationUseCase.Result.Error -> {
                    // TODO error handling
                }
            }
        }
    }
}