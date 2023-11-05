package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import com.example.satellitesinspace.data.model.SatelliteListItem
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    suspend fun getSatelliteListFromAPI(): Flow<Resource<List<SatelliteListItem>>>
    suspend fun getSatelliteDetailFromDB(satelliteID: Int): Flow<Resource<SatelliteDetailItemItem>>
    suspend fun getSatelliteDetailFromAPI(satelliteID: Int): Flow<Resource<SatelliteDetailItemItem>>
    suspend fun setSatelliteDetailToDB(satelliteDetailItemItem: SatelliteDetailItemItem)
}