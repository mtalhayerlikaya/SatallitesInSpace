package com.example.satellitesinspace.data.mapper

interface EntityMapper<NetworkModel, Entity> {
    fun mapToEntity(satelliteDetailModel: NetworkModel): Entity
    fun mapToSatelliteModel(satelliteDetailEntity: Entity): NetworkModel
}