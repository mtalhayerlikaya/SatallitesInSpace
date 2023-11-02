package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.domain.data_source.remote_data_source.RemoteSatelliteDataSource
import com.example.satellitesinspace.domain.repository.SatelliteRepository
import javax.inject.Inject

class SatelliteRepositoryImp
@Inject
constructor(private val remoteDataSource: RemoteSatelliteDataSource):SatelliteRepository {
    override fun satelliteList(): List<SatelliteListItem> {
        return remoteDataSource.getAllSatellites()
    }
}