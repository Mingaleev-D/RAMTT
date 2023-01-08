package com.example.ramtt.data.dto.charactermodel


import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String
)