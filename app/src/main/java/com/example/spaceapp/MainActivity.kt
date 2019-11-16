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
//import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_apod.*

class MainActivity : AppCompatActivity() {

    lateinit var apodFragment: ApodFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyAndConnect("https://api.nasa.gov/planetary/apod?api_key=mbXbdV3EpYydttCB6qbySduGMtYiS3TNyb0JagqH")

        //val bottomNavigation: BottomNavigationView = findViewById(R.id.btm_nav)
        this.apodFragment = ApodFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,apodFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    //Verify an existing connection
    fun verifyAndConnect(url:String = "oim"){
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
                jsonToObject(response)
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
        apodFragment.textViewTitle.text=apiResponse.title
        apodFragment.textViewExplanation.text = apiResponse.explanation
        apodFragment.textViewCopy.text=apiResponse.copyright
        apodFragment.textViewDate.text=apiResponse.date

        Picasso.get()
            .load(urlImagen)
            .resize(500,500)
            .into(apodFragment.imageViewApod)

        Toast.makeText(this, "Data Success", Toast.LENGTH_SHORT).show()
    }

}
