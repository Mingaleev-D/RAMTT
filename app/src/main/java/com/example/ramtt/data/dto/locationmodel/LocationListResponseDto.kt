package com.example.ramtt.data.dto.locationmodel


import com.google.gson.annotations.SerializedName

data class LocationListResponseDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<LocationResult>
)