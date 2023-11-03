package com.example.satellitesinspace.presentation.satellite_list

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.data.repository.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListViewModel
@Inject
constructor(private val satelliteRepository: SatelliteRepository):ViewModel() {

    suspend fun getAllSatellites(): List<SatelliteListItem> = satelliteRepository.satelliteList()

}