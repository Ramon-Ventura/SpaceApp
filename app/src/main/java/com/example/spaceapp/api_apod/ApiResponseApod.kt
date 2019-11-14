package com.example.spaceapp.api_apod


import com.google.gson.annotations.SerializedName

//APOD (Astronomy picture of the day)
data class ApiResponseApod(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    val title: String,
    val url: String
)