package com.morteza.swensonhe.domain

import com.morteza.swensonhe.data.fakes.LocationFakeData
import com.morteza.swensonhe.domain.repository.LocationRepository
import com.morteza.swensonhe.domain.usecase.GetLocationByIdUseCase
import com.morteza.swensonhe.utils.CoroutineTestRule
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
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetLocationByIdUseCaseTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = CoroutineTestRule()

    @Mock
    lateinit var locationRepository: LocationRepository

    lateinit var sut: GetLocationByIdUseCase

    @Before
    fun setUp() {
        sut = GetLocationByIdUseCase(locationRepository)
    }

    @Test
    fun `get location with valid id should result success with the location info`() =
        runTest {
            // Arrange (Given)
            val locationId = 11223344
            whenever(locationRepository.getLocationById(locationId)) doAnswer {
                LocationFakeData.getFakeLocation(
                    locationId
                )
            }

            // Act (When)
            val result = sut.invoke(locationId)

            // Assert (Then)
            TestCase.assertTrue(result is GetLocationByIdUseCase.Result.Success)
            TestCase.assertEquals(
                (result as GetLocationByIdUseCase.Result.Success).data.id,
                locationId
            )
        }

    @Test
    fun `get user with invalid id should result error`() =
        runTest {
            // Arrange (Given)
            val locationId = 11223344 // Invalid id
            whenever(locationRepository.getLocationById(locationId)) doAnswer { throw IOException() }

            // Act (When)
            val result = sut.invoke(locationId)

            // Assert (Then)
            TestCase.assertTrue(result is GetLocationByIdUseCase.Result.Error)
        }
}