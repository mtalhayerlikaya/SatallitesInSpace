package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteListItem
import retrofit2.Response

interface RemoteSatelliteDataSource {
    //suspend
    suspend fun getAllSatellites(): Response<List<SatelliteListItem>>
}