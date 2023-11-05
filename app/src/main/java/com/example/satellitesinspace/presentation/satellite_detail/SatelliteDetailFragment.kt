package com.example.satellitesinspace.presentation.satellite_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.databinding.FragmentSatelliteDetailBinding
import com.example.satellitesinspace.presentation.satellite_list.SatelliteListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
@AndroidEntryPoint
class SatelliteDetailFragment : Fragment() {
    private var _binding: FragmentSatelliteDetailBinding? = null
    private val binding get() = _binding!!
    private val satelliteListViewModel: SatelliteListViewModel by viewModels()
    private var satelliteId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSatelliteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: SatelliteDetailFragmentArgs by navArgs()
        satelliteId = bundle.clickedItemID
        CoroutineScope(Dispatchers.IO).launch {
            satelliteListViewModel.getSatelliteFromAPI(satelliteId)
        }
        observeFlow()
    }

    private fun observeFlow() {


        viewLifecycleOwner.lifecycleScope.launch {
            satelliteListViewModel.satelliteDetail.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        state.data?.let { satellite ->
                            println("***************"+satellite)
                        }
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}