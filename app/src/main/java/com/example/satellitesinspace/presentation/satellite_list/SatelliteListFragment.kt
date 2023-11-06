package com.example.satellitesinspace.presentation.satellite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.satellitesinspace.common.Resource
import com.example.satellitesinspace.common.SharedPref
import com.example.satellitesinspace.databinding.FragmentSatelliteListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SatelliteListFragment : Fragment() {

    private var _binding: FragmentSatelliteListBinding? = null
    private val binding get() = _binding!!
    private val satelliteListViewModel: SatelliteListViewModel by viewModels()
    private lateinit var satelliteRecyclerView: SatelliteRecyclerView

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
        CoroutineScope(Dispatchers.Main).launch {
            satelliteListViewModel.getAllSatellitesFromAPI()
        }
        observeFlow()
        searchSatellite()
    }

    private fun searchSatellite() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchedQuery ->
                        satelliteListViewModel.getSearchedSatelliteFromAPI(searchedQuery)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty()){
                    CoroutineScope(Dispatchers.IO).launch {
                        satelliteListViewModel.getAllSatellitesFromAPI()
                    }
                }
                return true
            }

        })

    }

    private fun observeFlow() {


        viewLifecycleOwner.lifecycleScope.launch {
            satelliteListViewModel.allSatellites.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        state.data?.let { satellite ->
                             satelliteRecyclerView = SatelliteRecyclerView(requireContext(), satellite.toMutableList()) {satelliteID,satelliteName->
                                SharedPref.getIsClickedBefore(satelliteID,requireContext())
                                val action =
                                    SatelliteListFragmentDirections.actionSatelliteListFragmentToSatelliteDetailFragment(satelliteID,satelliteName)
                                binding.root.findNavController().navigate(action)
                            }
                            binding.satelliteListRv.adapter = satelliteRecyclerView
                            binding.satelliteListRv.layoutManager =
                                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        }
                        binding.progressBarHome.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.progressBarHome.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        binding.progressBarHome.visibility = View.GONE
                    }
                    else -> {}
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            satelliteListViewModel.searchedSatellite.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        state.data?.let { satellite ->
                            satelliteRecyclerView.updateList(satellite.toMutableList())
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