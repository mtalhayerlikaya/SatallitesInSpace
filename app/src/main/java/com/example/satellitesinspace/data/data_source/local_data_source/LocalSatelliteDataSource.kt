package com.example.satellitesinspace.data.data_source.local_data_source

import com.example.satellitesinspace.data.model.SatelliteDetailItemItem

interface LocalSatelliteDataSource {
    suspend fun insertSatelliteDetail(satelliteItem: SatelliteDetailItemItem)
    suspend fun getSatelliteDetailFromDB(satelliteID: Int): SatelliteDetailItemItem
}