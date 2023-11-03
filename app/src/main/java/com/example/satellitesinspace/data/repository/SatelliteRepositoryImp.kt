package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteSatelliteDataSource
import javax.inject.Inject

class SatelliteRepositoryImp
@Inject
constructor(private val remoteDataSource: RemoteSatelliteDataSource): SatelliteRepository {
    override suspend fun satelliteList(): List<SatelliteListItem> {
        return remoteDataSource.getAllSatellites()
    }
}