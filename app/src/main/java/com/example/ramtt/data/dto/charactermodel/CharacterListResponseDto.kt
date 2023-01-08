package com.example.ramtt.data.dto.charactermodel


import com.google.gson.annotations.SerializedName

data class CharacterListResponseDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterResult>
)