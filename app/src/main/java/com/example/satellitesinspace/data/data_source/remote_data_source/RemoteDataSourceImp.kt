package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteListItem
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImp
@Inject
constructor(private val satelliteAPI: SatelliteAPI) : RemoteSatelliteDataSource {
    //suspend
    override suspend fun getAllSatellites(): List<SatelliteListItem> {
         return satelliteAPI.getAllSatellitesFromAPI().body()!!
    }


}