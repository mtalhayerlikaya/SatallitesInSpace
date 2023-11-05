package com.example.satellitesinspace.di

import android.content.Context
import androidx.room.Room
import com.example.satellitesinspace.data.data_source.local_data_source.SatelliteDAO
import com.example.satellitesinspace.data.data_source.local_data_source.SatelliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDAO(satelliteDB: SatelliteDatabase): SatelliteDAO = satelliteDB.satelliteDao()

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): SatelliteDatabase =
        Room.databaseBuilder(
            context,
            SatelliteDatabase::class.java,
            "satellite-database.db"
        ).build()

}