package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteSatelliteDataSource
import com.example.satellitesinspace.data.model.SatelliteListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SatelliteRepositoryImp
@Inject
constructor(private val remoteDataSource: RemoteSatelliteDataSource) : SatelliteRepository {
    override suspend fun satelliteList(): Flow<Resource<List<SatelliteListItem>>> =
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
}