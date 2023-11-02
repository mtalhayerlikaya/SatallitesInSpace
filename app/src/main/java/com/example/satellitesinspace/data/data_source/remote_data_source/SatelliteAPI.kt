package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteListItem
import retrofit2.http.GET

interface SatelliteAPI {
    //suspend
    @GET("satellite-list")
    fun getAllSatellitesFromAPI(): List<SatelliteListItem>
}