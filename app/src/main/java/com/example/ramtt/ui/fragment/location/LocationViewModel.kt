package com.example.ramtt.ui.fragment.location

import android.app.Application
import androidx.lifecycle.*
import com.example.ramtt.R
import com.example.ramtt.common.NetworkResource
import com.example.ramtt.common.isOnline
import com.example.ramtt.data.dto.charactermodel.CharacterListResponseDto
import com.example.ramtt.data.dto.locationmodel.LocationListResponseDto
import com.example.ramtt.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
   private val app: Application,
   private val repository: Repository
) : AndroidViewModel(app) {

   private var _locationResponse: MutableLiveData<NetworkResource<LocationListResponseDto>> =
      MutableLiveData()
   var locationResponse: LiveData<NetworkResource<LocationListResponseDto>> = _locationResponse

   fun getLocation() = viewModelScope.launch {
      _locationResponse.value = NetworkResource.Loading()

      if (isOnline(app)) {
         try {
            val response = repository.remote.fetchLocation()
            _locationResponse.value = handleCharacterItemResponse(response)

         } catch (e: Exception) {
            _locationResponse.value = NetworkResource.Error(R.string.data_not_found.toString())

         }
      } else {
         _locationResponse.value = NetworkResource.Error(R.string.no_internet_connected.toString())
      }
   }

   private fun handleCharacterItemResponse(response: Response<LocationListResponseDto>): NetworkResource<LocationListResponseDto>? {
      if (response.isSuccessful) {
         response.body()?.let { result ->
            return NetworkResource.Success(result)
         }
      }
      return NetworkResource.Error(response.message())
   }

}
