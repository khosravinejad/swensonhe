package com.morteza.swensonhe.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.morteza.swensonhe.data.fakes.LocationFakeData
import com.morteza.swensonhe.data.repository.LocationRepositoryImpl
import com.morteza.swensonhe.data.source.local.SwensonHeDatabase
import com.morteza.swensonhe.data.source.local.dao.LocationDao
import com.morteza.swensonhe.data.source.remote.ApiServices
import com.morteza.swensonhe.utils.CoroutineTestRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class LocationRepositoryImplTest {
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    private lateinit var database: SwensonHeDatabase
    private lateinit var locationDao: LocationDao
    private lateinit var context: Context
    lateinit var sut: LocationRepositoryImpl

    @Mock
    private lateinit var apiServices: ApiServices

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, SwensonHeDatabase::class.java)
            .allowMainThreadQueries().build()
        locationDao = database.locationDao

        sut = LocationRepositoryImpl(apiServices, database)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        dispatcher.run {
            database.clearAllTables()
        }
        database.close()
    }

    @Test
    fun `get location should return success location from local room`() =
        runTest {
            // Arrange (Given)
            val locationId = 11223344
            val locationEntity = LocationFakeData.getFakeLocation(locationId)
            // Saving location to database so we can get it when select query executes
            sut.saveLocation(locationEntity)

            // Act (When)
            val location = sut.getLocationById(locationId)

            // Assert (Then)
            TestCase.assertEquals(location.id, locationId)
        }
}