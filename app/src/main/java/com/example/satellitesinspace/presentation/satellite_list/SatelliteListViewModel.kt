package com.example.satellitesinspace.presentation.satellite_list

import androidx.lifecycle.ViewModel
import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.domain.repository.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatelliteListViewModel
@Inject
constructor(private val satelliteRepository: SatelliteRepository):ViewModel() {

    fun getAllSatellites(): List<SatelliteListItem> {
        return satelliteRepository.satelliteList()
    }


}