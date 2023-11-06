package com.example.satellitesinspace.presentation.satellite_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.common.SharedPref
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
    private var satelliteName by Delegates.notNull<String>()

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
        satelliteName = bundle.satelliteName
        CoroutineScope(Dispatchers.Main).launch {
            if (SharedPref.getIsClickedBefore(satelliteId, requireContext()))
                satelliteListViewModel.getSatelliteFromDB(satelliteId)
            else
                satelliteListViewModel.getSatelliteFromAPI(satelliteId)
            SharedPref.setIsClickedBefore(satelliteId, true, requireContext())
            satelliteListViewModel.getSatellitePositionFromAPI(satelliteId)
        }
        observeFlow()
        onBackPressed()
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(binding.textView).popBackStack()
            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun observeFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            satelliteListViewModel.satelliteDetail.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        state.data?.let { satellite ->
                            binding.hightMassRatio.text = "${satellite.height}/${satellite.mass}"
                            binding.satelliteDetailName.text = satelliteName
                            binding.cost.text = satellite.cost_per_launch.toString()
                            binding.firstFlight.text = satellite.first_flight
                        }
                        binding.progressBarDetail.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.progressBarDetail.visibility = View.VISIBLE

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        binding.progressBarDetail.visibility = View.GONE
                    }
                    else -> {}
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            satelliteListViewModel.satellitePosition.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        state.data?.let { position ->
                            binding.lastPosition.text = "(${position.posX},${position.posY})"
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