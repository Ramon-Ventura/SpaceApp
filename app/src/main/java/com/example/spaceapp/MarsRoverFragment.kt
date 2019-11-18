package com.example.spaceapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_mars_rover.*
import kotlinx.android.synthetic.main.fragment_mars_rover.view.*

/**
 * A simple [Fragment] subclass.
 */
class MarsRoverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_mars_rover, container, false)
        view.buttonSearchRover.setOnClickListener {
            (activity as MainActivity).verifyAndConnect("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=${editTextRover.text}&camera=fhaz&api_key=mbXbdV3EpYydttCB6qbySduGMtYiS3TNyb0JagqH")
        }
        return view
    }


}
