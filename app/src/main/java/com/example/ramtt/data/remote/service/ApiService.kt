package com.example.ramtt.data.remote.service

import com.example.ramtt.common.Constants.CHARACTER
import com.example.ramtt.common.Constants.EPISODE
import com.example.ramtt.common.Constants.LOCATION
import com.example.ramtt.data.dto.charactermodel.CharacterListResponseDto
import com.example.ramtt.data.dto.episodemodel.EpisodeListResponseDto
import com.example.ramtt.data.dto.locationmodel.LocationListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

interface ApiService {

   @GET(CHARACTER)
   suspend fun fetchCharacter():Response<CharacterListResponseDto>

   @GET(LOCATION)
   suspend fun fetchLocation():Response<LocationListResponseDto>

   @GET(EPISODE)
   suspend fun fetchEpisode():Response<EpisodeListResponseDto>

   @GET(CHARACTER)
   suspend fun fetchSearchCharacters(
      @Query("name") name: String
   ): Response<CharacterListResponseDto>

   @GET(LOCATION)
   suspend fun fetchSearchLocations(
      @Query("name") name: String
   ): Response<LocationListResponseDto>

   @GET(EPISODE)
   suspend fun fetchSearchEpisodes(
      @Query("name") name: String
   ): Response<EpisodeListResponseDto>
}