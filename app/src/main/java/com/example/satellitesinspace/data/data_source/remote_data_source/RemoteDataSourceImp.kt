package com.example.satellitesinspace.data.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.domain.data_source.remote_data_source.RemoteSatelliteDataSource
import javax.inject.Inject

class RemoteDataSourceImp
@Inject
constructor(private val satelliteAPI: SatelliteAPI) : RemoteSatelliteDataSource {
    //suspend
    override fun getAllSatellites(): List<SatelliteListItem> {
        return satelliteAPI.getAllSatellitesFromAPI()
    }


}