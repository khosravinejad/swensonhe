package com.morteza.swensonhe.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morteza.swensonhe.BuildConfig
import com.morteza.swensonhe.data.source.local.dao.LocationDao
import com.morteza.swensonhe.data.source.local.model.LocationCache

@Database(
    entities = [LocationCache::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class SwensonHeDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "swensonhe_android.db"
    }

    abstract val locationDao: LocationDao
}