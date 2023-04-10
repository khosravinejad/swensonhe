package com.morteza.swensonhe.di

import android.app.Application
import androidx.room.Room
import com.morteza.swensonhe.data.source.local.SwensonHeDatabase
import com.morteza.swensonhe.data.source.local.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return SwensonHeDatabase.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(@DatabaseInfo dbName: String, application: Application): SwensonHeDatabase {
        return Room.databaseBuilder(application, SwensonHeDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationDao(database: SwensonHeDatabase): LocationDao {
        return database.locationDao
    }
}