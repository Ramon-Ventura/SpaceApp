package com.example.spaceapp.api_solarsystem


import com.google.gson.annotations.SerializedName

data class ApiResponseSolarSystem(
    val density: Double,
    val englishName: String,
    val equaRadius: Double,
    val gravity: Double,
    val mass: Mass,
    val polarRadius: Double,
    val sideralOrbit: Double,
    val sideralRotation: Double,
    val vol: Vol
)