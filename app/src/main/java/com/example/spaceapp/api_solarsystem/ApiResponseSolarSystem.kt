package com.example.spaceapp.api_solarsystem


import com.google.gson.annotations.SerializedName

data class ApiResponseSolarSystem(
    val alternativeName: String,
    val aphelion: Int,
    val aroundPlanet: Any,
    val density: Double,
    val dimension: String,
    val discoveredBy: String,
    val discoveryDate: String,
    val eccentricity: Double,
    val englishName: String,
    val equaRadius: Double,
    val escape: Double,
    val flattening: Double,
    val gravity: Double,
    val id: String,
    val inclination: Double,
    val isPlanet: Boolean,
    val mass: Mass,
    val meanRadius: Double,
    val moons: List<Moon>,
    val name: String,
    val perihelion: Int,
    val polarRadius: Double,
    val semimajorAxis: Int,
    val sideralOrbit: Double,
    val sideralRotation: Double,
    val vol: Vol
)