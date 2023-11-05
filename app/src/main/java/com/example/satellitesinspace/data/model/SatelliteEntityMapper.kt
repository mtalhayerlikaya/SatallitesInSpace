package com.example.satellitesinspace.data.model

import com.example.satellitesinspace.data.data_source.local_data_source.SatelliteDetailEntity
import com.example.satellitesinspace.data.mapper.EntityMapper

class SatelliteDBEntityMapper: EntityMapper<SatelliteDetailItemItem,SatelliteDetailEntity> {
    override fun mapToEntity(satelliteDetailModel: SatelliteDetailItemItem): SatelliteDetailEntity {
        return SatelliteDetailEntity(
            satelliteID = satelliteDetailModel.id,
            costPerLaunch = satelliteDetailModel.cost_per_launch,
            firstFlight = satelliteDetailModel.first_flight,
            height = satelliteDetailModel.height,
            mass = satelliteDetailModel.mass
        )
    }

    override fun mapToSatelliteModel(satelliteDetailEntity: SatelliteDetailEntity): SatelliteDetailItemItem {
        return SatelliteDetailItemItem(
            id = satelliteDetailEntity.satelliteID,
            cost_per_launch = satelliteDetailEntity.costPerLaunch,
            first_flight = satelliteDetailEntity.firstFlight,
            height = satelliteDetailEntity.height,
            mass = satelliteDetailEntity.mass
        )
    }
}