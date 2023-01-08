package com.example.ramtt.data.remote.service

import com.example.ramtt.common.Constants.CHARACTER
import com.example.ramtt.data.dto.charactermodel.CharacterListResponseDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

interface ApiService {

   @GET(CHARACTER)
   suspend fun fetchCharacter():Response<CharacterListResponseDto>
}