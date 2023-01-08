package com.example.ramtt.ui.fragment.character

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

   var characterResponse: MutableLiveData<NetworkResource<CharacterListResponseDto>> =
      MutableLiveData()

   init {
      getCharacter()
   }

   fun getCharacter() = viewModelScope.launch {
      characterResponse.value = NetworkResource.Loading()

      if (isOnline(app)) {
         try {
            val response = repository.remote.fetchCharacter()
            characterResponse.value = handleCharacterItemResponse(response)

         } catch (e: Exception) {
            characterResponse.value = NetworkResource.Error(R.string.data_not_found.toString())

         }
      } else {
         characterResponse.value = NetworkResource.Error(R.string.no_internet_connected.toString())
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