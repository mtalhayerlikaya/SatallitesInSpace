package com.example.satellitesinspace.data.repository

import android.util.Log
import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.data_source.local_data_source.LocalSatelliteDataSource
import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteSatelliteDataSource
import com.example.satellitesinspace.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
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

  /*  override suspend fun getSatellitePositionFromAPI(satelliteID: Int): Flow<Resource<SatellitePositionList>> = flow {
        val response = remoteDataSource.getSatellitePositionFromAPI()
        if (response.isSuccessful) {
            if (response.body() != null) {
                response.body()?.let { satellitePositionList ->
                    satellitePositionList.list.forEach {
                        emit(Resource.Success(response.body()))
                    }
                }
            } else {
                emit(Resource.Error("Response is null"))
            }
        } else {
            emit(Resource.Error(response.message()))
        }
    }.flowOn(Dispatchers.IO)*/

    override suspend fun getSatellitePositionFromAPI(satelliteID: Int): Flow<Resource<Position>> = channelFlow {
        while (!isClosedForSend) {
            send(Resource.Loading)
            try {
                val response = remoteDataSource.getSatellitePositionFromAPI()
                response.body()?.let { positionList->
                    positionList.list.forEach {positions->
                        if(positions.id == satelliteID){
                            for (i in positions.positions.indices) {
                                send(Resource.Success(positions.positions[i]))
                                delay(3000)
                            }
                        }
                    }
                }
            } catch (throwable: Throwable) {
                send(Resource.Error(throwable.message ?: throwable.localizedMessage))
            }

        }
    }.flowOn(Dispatchers.IO)

}