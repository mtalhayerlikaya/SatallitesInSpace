package com.example.satellitesinspace.data.repository

import com.example.satellitesinspace.data.model.SatelliteListItem

interface SatelliteRepository {
    suspend fun satelliteList(): List<SatelliteListItem>
}