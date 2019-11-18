package com.example.spaceapp.api_mars_rovers


import com.google.gson.annotations.SerializedName

data class Rover(
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    @SerializedName("max_date")
    val maxDate: String,
    val status: String,
    @SerializedName("total_photos")
    val totalPhotos: Int
)