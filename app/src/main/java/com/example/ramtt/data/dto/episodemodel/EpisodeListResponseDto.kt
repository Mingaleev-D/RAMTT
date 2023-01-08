package com.example.ramtt.data.dto.episodemodel


import com.google.gson.annotations.SerializedName

data class EpisodeListResponseDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<EpisodeResult>
)