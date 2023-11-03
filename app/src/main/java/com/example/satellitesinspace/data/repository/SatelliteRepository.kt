package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.model.SatelliteListItem
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    suspend fun satelliteList(): Flow<Resource<List<SatelliteListItem>>>
}