package com.example.satellitesinspace.presentation.satellite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.satellitesinspace.common.Resource
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
            satelliteListViewModel.getAllSatellites()
        }
        observeFlow()
    }

    private fun observeFlow() {


        viewLifecycleOwner.lifecycleScope.launch {
            satelliteListViewModel.allSatellites.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        state.data?.let {satellite->
                            val satelliteRecyclerView = SatelliteRecyclerView(requireContext(),satellite)
                            binding.satelliteListRv.adapter = satelliteRecyclerView
                            binding.satelliteListRv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                        }
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(),state.message,Toast.LENGTH_SHORT).show()
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