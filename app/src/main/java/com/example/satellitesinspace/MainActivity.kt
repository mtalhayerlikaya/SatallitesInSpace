package com.example.satellitesinspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.satellitesinspace.common.AppUtil
import com.example.satellitesinspace.databinding.ActivityMainBinding
import com.example.satellitesinspace.presentation.satellite_list.SatelliteListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppUtil.myApplication = application

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}