package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.data.model.SatellitePositionList
import com.example.satellitesinspace.data.model.SatellitePositions
import retrofit2.Response

interface RemoteSatelliteDataSource {
    //suspend
    suspend fun getAllSatellites(): Response<List<SatelliteListItem>>
    suspend fun getAllSatellitesDetail(): Response<List<SatelliteDetailItemItem>>
    suspend fun getSatellitePositionFromAPI(): Response<SatellitePositionList>
}