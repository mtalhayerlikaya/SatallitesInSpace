package com.example.satellitesinspace.di

import com.example.satellitesinspace.data.data_source.local_data_source.LocalDataSourceImp
import com.example.satellitesinspace.data.data_source.local_data_source.LocalSatelliteDataSource
import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteDataSourceImp
import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteSatelliteDataSource
import com.example.satellitesinspace.data.repository.SatelliteRepository
import com.example.satellitesinspace.data.repository.SatelliteRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSatelliteRepository(
        remoteSatelliteDataSource: RemoteSatelliteDataSource,
        localDataSource: LocalSatelliteDataSource
    ): SatelliteRepository =
        SatelliteRepositoryImp(remoteSatelliteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideRemoteSatelliteRepositoryImp(
        remoteDataSource: RemoteDataSourceImp
    ): RemoteSatelliteDataSource = remoteDataSource

    @Provides
    @Singleton
    fun provideLocalSatelliteRepositoryImp(
        localDataSource: LocalDataSourceImp
    ): LocalSatelliteDataSource = localDataSource

}