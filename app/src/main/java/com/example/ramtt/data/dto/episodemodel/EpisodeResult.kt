package com.example.ramtt.data.dto.episodemodel


import com.google.gson.annotations.SerializedName

data class EpisodeResult(
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)