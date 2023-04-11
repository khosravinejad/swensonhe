package com.morteza.swensonhe.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.morteza.swensonhe.domain.model.Location
import com.morteza.swensonhe.domain.usecase.SearchLocationUseCase
import com.morteza.swensonhe.presentation.base.BaseViewModel
import com.morteza.swensonhe.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val searchLocationUseCase: SearchLocationUseCase
) : BaseViewModel(contextProvider) {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO handle coroutine error
    }

    private val _locations: MutableLiveData<List<Location>> = MutableLiveData()
    val locations: LiveData<List<Location>> = _locations

    fun searchLocation(query: String) {
        launchCoroutineIO {
            when (val result = searchLocationUseCase.invoke(query)) {
                is SearchLocationUseCase.Result.Success -> {
                    _locations.postValue(result.data)
                }
                is SearchLocationUseCase.Result.Error -> {
                    // TODO error handling
                }
            }
        }
    }
}