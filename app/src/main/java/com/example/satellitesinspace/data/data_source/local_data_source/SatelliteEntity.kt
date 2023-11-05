package com.example.satellitesinspace.data.data_source.local_data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "satellite")
data class SatelliteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val satelliteID: Int,
    @ColumnInfo(name = "active")
    val active: String,
    @ColumnInfo(name = "name")
    val name: String
)
