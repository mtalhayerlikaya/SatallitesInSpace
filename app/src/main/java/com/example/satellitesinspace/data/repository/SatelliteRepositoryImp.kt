package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.data_source.local_data_source.LocalSatelliteDataSource
import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteSatelliteDataSource
import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import com.example.satellitesinspace.data.model.SatelliteListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SatelliteRepositoryImp
@Inject
constructor(private val remoteDataSource: RemoteSatelliteDataSource,
            private val localDataSource: LocalSatelliteDataSource
) : SatelliteRepository {
    override suspend fun getSatelliteListFromAPI(): Flow<Resource<List<SatelliteListItem>>> =
        flow {
            val response = remoteDataSource.getAllSatellites()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    emit(Resource.Success(response.body()))
                } else {
                    emit(Resource.Error("Response is null"))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getSatelliteDetailFromDB(satelliteID: Int): Flow<Resource<SatelliteDetailItemItem>> = flow {
        val response = localDataSource.getSatelliteDetailFromDB(satelliteID)
        emit(Resource.Success(response))
    }.flowOn(Dispatchers.IO)

    override suspend fun getSatelliteDetailFromAPI(satelliteID: Int): Flow<Resource<SatelliteDetailItemItem>> =
        flow {
            val response = remoteDataSource.getAllSatellitesDetail()

            if (response.isSuccessful) {
                if (response.body() != null) {
                    response.body()!!.forEach {satellite->
                        if(satellite.id == satelliteID) {
                            setSatelliteDetailToDB(satellite)
                            return@forEach emit(Resource.Success(satellite))
                        }
                    }
                } else {
                    emit(Resource.Error("Response is null"))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun setSatelliteDetailToDB(satelliteDetailItemItem: SatelliteDetailItemItem) {
        localDataSource.insertSatelliteDetail(satelliteDetailItemItem)
    }
}