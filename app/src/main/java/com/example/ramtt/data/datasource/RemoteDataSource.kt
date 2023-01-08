package com.example.ramtt.data.datasource

import com.example.ramtt.data.dto.charactermodel.CharacterListResponseDto
import com.example.ramtt.data.dto.episodemodel.EpisodeListResponseDto
import com.example.ramtt.data.dto.locationmodel.LocationListResponseDto
import com.example.ramtt.data.remote.service.ApiService
import retrofit2.Response
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

class RemoteDataSource @Inject constructor(
   private val api: ApiService
) {

   suspend fun fetchCharacter(): Response<CharacterListResponseDto> {
      return api.fetchCharacter()
   }

   suspend fun fetchLocation(): Response<LocationListResponseDto> {
      return api.fetchLocation()
   }

   suspend fun fetchEpisode(): Response<EpisodeListResponseDto> {
      return api.fetchEpisode()
   }

   suspend fun fetchSearchCharacter(name:String):Response<CharacterListResponseDto>{
      return api.fetchSearchCharacters(name)
   }

   suspend fun fetchSearchLocation(name:String):Response<LocationListResponseDto>{
      return api.fetchSearchLocations(name)
   }

   suspend fun fetchSearchEpisode(name:String):Response<EpisodeListResponseDto>{
      return api.fetchSearchEpisodes(name)
   }
}