package com.example.satellitesinspace.data.data_source.local_data_source

import com.example.satellitesinspace.data.model.CryptoDBEntityMapper
import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import javax.inject.Inject

class LocalDataSourceImp
@Inject
constructor(private val satelliteDAO: SatelliteDAO) : LocalSatelliteDataSource {
    override suspend fun insertSatelliteDetail(satelliteItem: SatelliteDetailItemItem) {
        satelliteDAO.insertSatelliteDetail(CryptoDBEntityMapper().mapToEntity(satelliteItem))
    }

    override suspend fun getSatelliteDetailFromDB(satelliteID: Int): SatelliteDetailItemItem {
        val satelliteDetail = satelliteDAO.getSatelliteDetailFromDB(satelliteID)
        return CryptoDBEntityMapper().mapToSatelliteModel(satelliteDetail)
    }
}