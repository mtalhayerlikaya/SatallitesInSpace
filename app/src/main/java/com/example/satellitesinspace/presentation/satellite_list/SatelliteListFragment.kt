package com.example.satellitesinspace.presentation.satellite_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.satellitesinspace.R
import com.example.satellitesinspace.databinding.FragmentSatelliteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatelliteListFragment : Fragment() {

    private var _binding: FragmentSatelliteListBinding? = null
    private val binding get() = _binding!!
    private val satelliteListViewModel by viewModels<SatelliteListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSatelliteListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        satelliteListViewModel.getAllSatellites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}