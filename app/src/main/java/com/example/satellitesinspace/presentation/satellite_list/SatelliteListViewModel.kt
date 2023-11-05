package com.example.satellitesinspace.presentation.satellite_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.data.model.SatelliteDetailItemItem
import com.example.satellitesinspace.data.model.SatelliteListItem
import com.example.satellitesinspace.data.repository.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteListViewModel
@Inject
constructor(private val satelliteRepository: SatelliteRepository) : ViewModel() {

    private val _allSatellites =
        MutableStateFlow<Resource<List<SatelliteListItem>>>(Resource.Empty)

    val allSatellites: StateFlow<Resource<List<SatelliteListItem>>> =
        _allSatellites

    private val _satelliteDetail =
        MutableStateFlow<Resource<SatelliteDetailItemItem>>(Resource.Empty)

    val satelliteDetail: StateFlow<Resource<SatelliteDetailItemItem>> =
        _satelliteDetail

    suspend fun getAllSatellitesFromAPI() = viewModelScope.launch {
        satelliteRepository.getSatelliteListFromAPI().collect(collector = {satelliteList->
            _allSatellites.value = satelliteList
        })
    }

    suspend fun getSatelliteFromAPI(satelliteID: Int)= viewModelScope.launch {
        satelliteRepository.getSatelliteDetailFromAPI(satelliteID).collect(collector = {satelliteDetail->
            _satelliteDetail.value = satelliteDetail
        })
    }
    suspend fun getSatelliteFromDB(satelliteID: Int)= viewModelScope.launch {
        satelliteRepository.getSatelliteDetailFromDB(satelliteID).collect(collector = {satelliteDetail->
            _satelliteDetail.value = satelliteDetail
        })
    }
}