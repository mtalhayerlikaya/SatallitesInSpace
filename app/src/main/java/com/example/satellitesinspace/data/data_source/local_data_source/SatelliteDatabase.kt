package com.example.satellitesinspace.data.data_source.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SatelliteDetailEntity::class], version = 1)
abstract class SatelliteDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDAO
}