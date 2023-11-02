package com.example.satellitesinspace.di

import com.example.satellitesinspace.data.data_source.remote_data_source.RemoteDataSourceImp
import com.example.satellitesinspace.data.data_source.remote_data_source.SatelliteAPI
import com.example.satellitesinspace.data.repository.SatelliteRepositoryImp
import com.example.satellitesinspace.domain.data_source.remote_data_source.RemoteSatelliteDataSource
import com.example.satellitesinspace.domain.repository.SatelliteRepository
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
    fun provideSatelliteRepository(remoteSatelliteDataSource: RemoteSatelliteDataSource): SatelliteRepository =
        SatelliteRepositoryImp(remoteSatelliteDataSource)

    @Provides
    @Singleton
    fun provideRemoteSatelliteDataSource(
        satelliteAPI: SatelliteAPI
    ): RemoteSatelliteDataSource = RemoteDataSourceImp(satelliteAPI)

}