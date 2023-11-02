package com.example.satellitesinspace.domain.data_source.remote_data_source

import com.example.satellitesinspace.data.model.SatelliteList
import com.example.satellitesinspace.data.model.SatelliteListItem

interface RemoteSatelliteDataSource {
    //suspend
     fun getAllSatellites(): List<SatelliteListItem>
}