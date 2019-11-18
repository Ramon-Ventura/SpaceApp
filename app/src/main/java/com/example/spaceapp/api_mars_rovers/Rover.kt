package com.example.spaceapp.api_mars_rovers


import com.google.gson.annotations.SerializedName

data class Rover(
    val id: Int,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    @SerializedName("max_date")
    val maxDate: String,
    @SerializedName("max_sol")
    val maxSol: Int,
    val name: String,
    val status: String,
    @SerializedName("total_photos")
    val totalPhotos: Int
)