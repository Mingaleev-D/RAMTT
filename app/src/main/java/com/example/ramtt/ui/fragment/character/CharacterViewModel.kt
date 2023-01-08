package com.example.ramtt.ui.fragment.character

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ramtt.R
import com.example.ramtt.common.NetworkResource
import com.example.ramtt.common.isOnline
import com.example.ramtt.data.dto.charactermodel.CharacterListResponseDto
import com.example.ramtt.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
   private val app: Application,
   private val repository: Repository
) : AndroidViewModel(app) {

   private var _characterResponse: MutableLiveData<NetworkResource<CharacterListResponseDto>> =
      MutableLiveData()
   var characterResponse:LiveData<NetworkResource<CharacterListResponseDto>> = _characterResponse


   fun getCharacter() = viewModelScope.launch {
      _characterResponse.value = NetworkResource.Loading()

      if (isOnline(app)) {
         try {
            val response = repository.remote.fetchCharacter()
            _characterResponse.value = handleCharacterItemResponse(response)

         } catch (e: Exception) {
            _characterResponse.value = NetworkResource.Error(R.string.data_not_found.toString())

         }
      } else {
         _characterResponse.value = NetworkResource.Error(R.string.no_internet_connected.toString())
      }
   }

   private fun handleCharacterItemResponse(response: Response<CharacterListResponseDto>): NetworkResource<CharacterListResponseDto>? {
      if (response.isSuccessful) {
         response.body()?.let { result ->
            return NetworkResource.Success(result)
         }
      }
      return NetworkResource.Error(response.message())
   }

}