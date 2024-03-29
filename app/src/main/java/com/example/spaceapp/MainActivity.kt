package com.example.spaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.spaceapp.api_apod.ApiResponseApod
import com.example.spaceapp.api_mars_rovers.ApiResponseMarsRover
import com.example.spaceapp.api_solarsystem.ApiResponseSolarSystem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_apod.*
import kotlinx.android.synthetic.main.fragment_mars_rover.*
import kotlinx.android.synthetic.main.fragment_solar_system.*

class MainActivity : AppCompatActivity() {


    //Objects for fragments
    lateinit var apodFragment: ApodFragment
    lateinit var solarSystemFragment: SolarSystemFragment
    lateinit var marsRoverFragment: MarsRoverFragment

    //Verify which fragment is active
    var isSolarSystem : Boolean = false
    var isApod : Boolean = false
    var isMarsRover : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.btm_nav)
        this.apodFragment = ApodFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,apodFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        restoreFragments()
        isApod=true
        verifyAndConnect("https://api.nasa.gov/planetary/apod?api_key=mbXbdV3EpYydttCB6qbySduGMtYiS3TNyb0JagqH")

        //Listener for the bottom navigation menu
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            //When user tap apod option
            when (item.itemId){
                R.id.Apod->{
                    apodFragment = ApodFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,apodFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    restoreFragments()
                    isApod=true
                    verifyAndConnect("https://api.nasa.gov/planetary/apod?api_key=mbXbdV3EpYydttCB6qbySduGMtYiS3TNyb0JagqH")
                }
                //When user tap SolarSystem option
                R.id.SolarSystem ->{
                    solarSystemFragment = SolarSystemFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,solarSystemFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    restoreFragments()
                    isSolarSystem=true
                    verifyAndConnect("https://api.le-systeme-solaire.net/rest/bodies/earth")
                }
                //When user tap Mars Rover option
                R.id.MarsRover ->{
                    marsRoverFragment = MarsRoverFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,marsRoverFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    restoreFragments()
                    isMarsRover=true
                    verifyAndConnect("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2018-6-3&camera=fhaz&api_key=mbXbdV3EpYydttCB6qbySduGMtYiS3TNyb0JagqH")
                }
            }

            true
        }
    }

    //Verify an existing connection
    fun verifyAndConnect(url:String){
        if(Network.verifyConection(this)){
            httpVolley(url)
        }else{
            Toast.makeText(this,"No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun httpVolley(url: String){
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                Log.d("HTTPVolley",  response)
                Toast.makeText(this, "Connection Success", Toast.LENGTH_LONG).show()
                if(isSolarSystem){
                    jsonToObjectSolarSystem(response)
                }
                else if(isApod){
                    jsonToObject(response)
                }
                else if(isMarsRover){
                    jsonToObjectoMarsRover(response)
                }

            },
            Response.ErrorListener {
                Log.d("HTTPVolley", "Error en la URL $url")
                Toast.makeText(this, "¡An error has occurred!", Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)

    }

    private fun jsonToObject(response: String){
        val gson= Gson()
        val apiResponse = gson.fromJson(response, ApiResponseApod::class.java)
        val urlImagen = apiResponse.hdurl
        apodFragment.textViewTitle.text=getString(R.string.text_view_title,apiResponse.title)
        apodFragment.textViewExplanation.text = apiResponse.explanation
        apodFragment.textViewCopy.text=apiResponse.copyright
        apodFragment.textViewDate.text=apiResponse.date

        Picasso.get()
            .load(urlImagen)
            .placeholder(R.drawable.moon)
            .error(R.drawable.moon)
            .resize(500,500)
            .into(apodFragment.imageViewApod)

        Toast.makeText(this, "Data Success", Toast.LENGTH_SHORT).show()
    }

    private fun jsonToObjectSolarSystem(response: String){
        val gson= Gson()
        val apiResponse = gson.fromJson(response, ApiResponseSolarSystem::class.java)
        solarSystemFragment.textViewPlanetName.text=apiResponse.englishName
        solarSystemFragment.textViewMassValue.text=apiResponse.mass.massValue.toString()
        solarSystemFragment.textViewMassExponent.text=apiResponse.mass.massExponent.toString()
        solarSystemFragment.textViewVolumeValue.text=apiResponse.vol.volValue.toString()
        solarSystemFragment.textViewVolumeExponent.text=apiResponse.vol.volExponent.toString()

         solarSystemFragment.textViewDensityValue.text=getString(R.string.text_view_density_value,apiResponse.density)
        solarSystemFragment.textViewGravityValue.text=getString(R.string.text_view_gravity_value,apiResponse.gravity)
        solarSystemFragment.textViewEquatorialRadiusValue.text=getString(R.string.text_view_equatorial_radius_value,apiResponse.equaRadius)
        solarSystemFragment.textViewPolarRadiusValue.text=getString(R.string.text_view_polar_radius_value,apiResponse.polarRadius)
        solarSystemFragment.textViewSiderealOrbitValue.text=getString(R.string.text_view_sidereal_orbit_value,apiResponse.sideralOrbit)
        solarSystemFragment.textViewSiderealRotationValue.text=getString(R.string.text_view_sidereal_rotation_value,apiResponse.sideralRotation)


        Toast.makeText(this, "Data Success", Toast.LENGTH_SHORT).show()
    }

    private fun jsonToObjectoMarsRover(response: String){
        val gson= Gson()
        val apiResponse = gson.fromJson(response, ApiResponseMarsRover::class.java)
        marsRoverFragment.textViewEarthDateValue.text =  apiResponse.photos.first().earthDate
        marsRoverFragment.textViewCameraTypeValue.text = apiResponse.photos.first().camera.fullName
        marsRoverFragment.textViewLandingDateValue.text=apiResponse.photos.first().rover.landingDate
        marsRoverFragment.textViewLaunchDateValue.text=apiResponse.photos.first().rover.launchDate
        marsRoverFragment.textViewMaxDateValue.text=apiResponse.photos.first().rover.maxDate
        marsRoverFragment.textViewStatusValue.text=apiResponse.photos.first().rover.status
        marsRoverFragment.textViewPhotosValue.text=apiResponse.photos.first().rover.totalPhotos.toString()
        val urlImageRover = apiResponse.photos.first().imgSrc
        val urlRover = urlImageRover.replace("http","https")

        Picasso.get()
            .load(urlRover)
            .resize(500,500)
            .into(marsRoverFragment.imageViewRoverPhoto)

        Toast.makeText(this, "Data Success", Toast.LENGTH_SHORT).show()
    }

    private fun restoreFragments(){
        isApod=false
        isSolarSystem=false
        isMarsRover= false
    }

}
