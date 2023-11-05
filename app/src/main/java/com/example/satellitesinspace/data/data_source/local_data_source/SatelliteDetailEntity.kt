package com.example.satellitesinspace.data.data_source.local_data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "satellite_detail")
data class SatelliteDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val satelliteID: Int,
    @ColumnInfo(name = "cost_per_launch")
    val costPerLaunch: Int,
    @ColumnInfo(name = "first_flight")
    val firstFlight: String,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "mass")
    val mass: Int)
