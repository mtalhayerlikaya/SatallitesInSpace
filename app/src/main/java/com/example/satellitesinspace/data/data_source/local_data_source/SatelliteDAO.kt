package com.example.satellitesinspace.data.data_source.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SatelliteDAO {

    @Insert
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailEntity)

    @Query("SELECT * FROM satellite_detail WHERE id = :satelliteId")
    suspend fun getSatelliteDetailFromDB(satelliteId: Int): SatelliteDetailEntity


}