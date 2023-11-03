package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteListItem
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface SatelliteAPI {
    //suspend
    @GET("satellite-list")
    suspend fun getAllSatellitesFromAPI(): Response<List<SatelliteListItem>>
}