package com.example.ramtt.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ramtt.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

   private var _binding: FragmentHomeBinding? = null
   private val binding by lazy { _binding!! }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.btnCharacter.setOnClickListener {
         val action = HomeFragmentDirections.actionHomeFragmentToCharacterFragment()
         findNavController().navigate(action)
      }
//      binding.btnEpisode.setOnClickListener {
//         val action = HomeFragmentDirections.actionHomeFragmentToEpisodeFragment()
//         findNavController().navigate(action)
//      }
//      binding.btnLocation.setOnClickListener {
//         val action = HomeFragmentDirections.actionHomeFragmentToLocationsFragment()
//         findNavController().navigate(action)
//      }

   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

}