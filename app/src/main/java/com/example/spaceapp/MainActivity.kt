package com.example.spaceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.spaceapp.api_apod.ApiResponseApod
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verifyAndConnect()
    }

    //Verify an existing connection
    private fun verifyAndConnect(){
        if(Network.verifyConection(this)){
            httpVolley(getUrlApi())
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

    private fun getUrlApi (): String{
        return "https://api.nasa.gov/planetary/apod?api_key=mbXbdV3EpYydttCB6qbySduGMtYiS3TNyb0JagqH"
    }

    private fun jsonToObject(response: String){
        val gson= Gson()
        val apiResponse = gson.fromJson(response, ApiResponseApod::class.java)
        textViewTitle.text=apiResponse.title
        Toast.makeText(this, "Data Success", Toast.LENGTH_SHORT).show()
    }

}
