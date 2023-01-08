package com.example.ramtt.ui.fragment.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ramtt.R
import com.example.ramtt.common.NetworkResource
import com.example.ramtt.databinding.FragmentEpisodeBinding
import com.example.ramtt.ui.fragment.episode.adapter.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment : Fragment() {

   private var _binding: FragmentEpisodeBinding? = null
   private val binding by lazy { _binding!! }
   private val viewModel by viewModels<EpisodeViewModel>()
   private val episodeAdapter = EpisodeAdapter()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      initRecyclerView()
      viewModelInit()

   }

   private fun viewModelInit() {
      lifecycleScope.launch {
         viewModel.getLocation()
         viewModel.episodeResponse.observe(viewLifecycleOwner){response ->
            when(response){
               is NetworkResource.Loading -> {
                  binding.rvEpisode.showShimmer()
               }
               is NetworkResource.Success -> {
                  binding.rvEpisode.hideShimmer()
                  response.data?.let {
                     episodeAdapter.differ.submitList(it.results)
                  }
               }
               is NetworkResource.Error   -> {
                  binding.errorImage.visibility = View.VISIBLE
                  binding.errortxt.visibility = View.VISIBLE
                  Toast.makeText(requireContext(), getString(R.string.no_internet_connected), Toast.LENGTH_SHORT).show()
               }
            }
         }
      }
   }

   private fun initRecyclerView() {
      with(binding.rvEpisode) {
         adapter = episodeAdapter
         overScrollMode = android.view.View.OVER_SCROLL_NEVER
         setHasFixedSize(true)
         layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

}