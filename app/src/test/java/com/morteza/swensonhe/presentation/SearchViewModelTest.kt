package com.morteza.swensonhe.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.morteza.swensonhe.data.fakes.LocationFakeData
import com.morteza.swensonhe.domain.usecase.SearchLocationUseCase
import com.morteza.swensonhe.presentation.search.SearchViewModel
import com.morteza.swensonhe.utils.LiveDataTestUtil
import com.morteza.swensonhe.utils.TestContextProvider
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val dispatcher = TestContextProvider()

    @Mock
    lateinit var searchLocationUseCase: SearchLocationUseCase

    private lateinit var sut: SearchViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = SearchViewModel(
            dispatcher,
            searchLocationUseCase
        )
    }

    @Test
    fun `getting location info successfully`() =
        runTest {
            val locationId = 12
            val query = "ist"
            val location = LocationFakeData.getFakeLocation(locationId)

            whenever(searchLocationUseCase.invoke(query)) doAnswer {
                SearchLocationUseCase.Result.Success(
                    listOf(location)
                )
            }

            sut.searchLocation(query)
            val result = LiveDataTestUtil.getValue(sut.locations)

            TestCase.assertEquals(result.size, 1)
            TestCase.assertEquals(result[0].id, 12)
        }
}