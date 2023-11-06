package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.model.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SatelliteRepository {
    suspend fun getSatelliteListFromAPI(): Flow<Resource<List<SatelliteListItem>>>
    suspend fun getSatelliteDetailFromDB(satelliteID: Int): Flow<Resource<SatelliteDetailItemItem>>
    suspend fun getSatelliteDetailFromAPI(satelliteID: Int): Flow<Resource<SatelliteDetailItemItem>>
    suspend fun setSatelliteDetailToDB(satelliteDetailItemItem: SatelliteDetailItemItem)
    suspend fun getSatellitePositionFromAPI(satelliteID: Int): Flow<Resource<Position>>
    suspend fun getSearchedSatelliteFromAPI(searchedQuery: String): Flow<Resource<List<SatelliteListItem>>>
}