package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import com.example.satellitesinspace.data.model.SatelliteListItem
import retrofit2.Response
import retrofit2.http.GET

interface SatelliteAPI {
    @GET("satellite-list")
    suspend fun getAllSatellitesFromAPI(): Response<List<SatelliteListItem>>

    @GET("satellite-detail")
    suspend fun getAllSatellitesDetailFromAPI(): Response<List<SatelliteDetailItemItem>>
}