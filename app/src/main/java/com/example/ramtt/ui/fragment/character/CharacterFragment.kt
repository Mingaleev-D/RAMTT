package com.example.ramtt.ui.fragment.character

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ramtt.R
import com.example.ramtt.common.NetworkResource
import com.example.ramtt.databinding.FragmentCharacterBinding
import com.example.ramtt.ui.fragment.character.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {

   private var _binding: FragmentCharacterBinding? = null
   private val binding by lazy { _binding!! }
   private val viewModel by viewModels<CharacterViewModel>()
   private val characterAdapter = CharacterAdapter()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentCharacterBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      initRecyclerView()
      viewModelInit()
      initSearch()

   }

   private fun viewModelInit() {
      lifecycleScope.launch {
         viewModel.getCharacter()
         viewModel.characterResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
               is NetworkResource.Loading -> {
                  binding.rcCharacter.showShimmer()
               }
               is NetworkResource.Success -> {
                  binding.rcCharacter.hideShimmer()
                  response.data?.let {
                     characterAdapter.differ.submitList(it.results)
                  }
               }
               is NetworkResource.Error   -> {
                  binding.errorImage.visibility = View.VISIBLE
                  binding.errortxt.visibility = View.VISIBLE
                  Toast.makeText(
                     requireContext(),
                     getString(R.string.no_internet_connected),
                     Toast.LENGTH_SHORT
                  ).show()
               }
            }
         }
      }
   }

   private fun initRecyclerView() {
      with(binding.rcCharacter) {
         adapter = characterAdapter
         overScrollMode = View.OVER_SCROLL_NEVER
         setHasFixedSize(true)
         layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      }
   }

   private fun initSearch() {
      var job: Job? = null
      binding.textInputEt.addTextChangedListener { text: Editable? ->
         job?.cancel()
         job = MainScope().launch {
            delay(500L)
            text?.let {
               if (it.toString().isNotEmpty()) {
                  viewModel.getSearchCharacter(it.toString())
               }
            }
         }
      }
      viewModel.searchCharacterResponse.observe(viewLifecycleOwner){searchResponse ->
         when(searchResponse){
            is NetworkResource.Loading -> {
               binding.rcCharacter.showShimmer()
            }
            is NetworkResource.Success -> {
               binding.rcCharacter.hideShimmer()
               searchResponse.data?.let {
                  characterAdapter.differ.submitList(it.results)
               }
            }
            is NetworkResource.Error   -> {
               binding.rcCharacter.hideShimmer()
               Toast.makeText(requireContext(), "data not found", Toast.LENGTH_SHORT).show()
            }
         }
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}