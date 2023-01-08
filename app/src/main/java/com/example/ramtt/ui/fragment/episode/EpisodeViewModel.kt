package com.example.ramtt.ui.fragment.episode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ramtt.R
import com.example.ramtt.common.NetworkResource
import com.example.ramtt.common.isOnline
import com.example.ramtt.data.dto.episodemodel.EpisodeListResponseDto
import com.example.ramtt.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
   private val app: Application,
   private val repository: Repository
) : AndroidViewModel(app) {

   private var _episodeResponse: MutableLiveData<NetworkResource<EpisodeListResponseDto>> =
      MutableLiveData()
   var episodeResponse: LiveData<NetworkResource<EpisodeListResponseDto>> = _episodeResponse

   fun getLocation() = viewModelScope.launch {
      _episodeResponse.value = NetworkResource.Loading()

      if (isOnline(app)) {
         try {
            val response = repository.remote.fetchEpisode()
            _episodeResponse.value = handleCharacterItemResponse(response)

         } catch (e: Exception) {
            _episodeResponse.value = NetworkResource.Error(R.string.data_not_found.toString())

         }
      } else {
         _episodeResponse.value = NetworkResource.Error(R.string.no_internet_connected.toString())
      }
   }

   private fun handleCharacterItemResponse(response: Response<EpisodeListResponseDto>): NetworkResource<EpisodeListResponseDto>? {
      if (response.isSuccessful) {
         response.body()?.let { result ->
            return NetworkResource.Success(result)
         }
      }
      return NetworkResource.Error(response.message())
   }

}
