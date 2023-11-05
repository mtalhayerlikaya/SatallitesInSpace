package com.example.satellitesinspace.data.model

data class SatellitePositionList(
    val list: List<SatellitePositions>
)

data class SatellitePositions(
    val id: Int,
    val positions: List<Position>
)