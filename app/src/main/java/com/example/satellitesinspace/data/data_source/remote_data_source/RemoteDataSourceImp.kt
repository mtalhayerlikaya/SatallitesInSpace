package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import com.example.satellitesinspace.data.model.SatelliteListItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImp
@Inject
constructor(private val satelliteAPI: SatelliteAPI) : RemoteSatelliteDataSource {

    override suspend fun getAllSatellites(): Response<List<SatelliteListItem>> = satelliteAPI.getAllSatellitesFromAPI()
    override suspend fun getAllSatellitesDetail(): Response<List<SatelliteDetailItemItem>> = satelliteAPI.getAllSatellitesDetailFromAPI()

}