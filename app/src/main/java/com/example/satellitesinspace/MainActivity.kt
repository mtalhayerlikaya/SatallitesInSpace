package com.example.satellitesinspace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.satellitesinspace.common.AppUtil
import com.example.satellitesinspace.databinding.ActivityMainBinding
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