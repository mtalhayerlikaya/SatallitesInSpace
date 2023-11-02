package com.example.satellitesinspace.domain.repository

import com.example.satellitesinspace.data.model.SatelliteListItem

interface SatelliteRepository {
    fun satelliteList(): List<SatelliteListItem>
}