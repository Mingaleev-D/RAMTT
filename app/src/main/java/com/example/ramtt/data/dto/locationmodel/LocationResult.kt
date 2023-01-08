package com.example.ramtt.data.dto.locationmodel


import com.google.gson.annotations.SerializedName

data class LocationResult(
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,

)