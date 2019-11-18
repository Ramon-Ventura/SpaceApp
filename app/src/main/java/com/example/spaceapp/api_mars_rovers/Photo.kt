package com.example.spaceapp.api_mars_rovers


import com.google.gson.annotations.SerializedName

data class Photo(
    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,

    @SerializedName("img_src")
    val imgSrc: String,
    val rover: Rover

)