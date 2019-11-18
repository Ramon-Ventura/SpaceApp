package com.example.spaceapp.api_mars_rovers


import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("full_name")
    val fullName: String
)