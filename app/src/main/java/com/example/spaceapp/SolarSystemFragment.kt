package com.example.spaceapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_solar_system.*
import kotlinx.android.synthetic.main.fragment_solar_system.view.*
import kotlinx.android.synthetic.main.fragment_solar_system.view.textViewPlanetName

/**
 * A simple [Fragment] subclass.
 */
class SolarSystemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_solar_system, container, false)
        // Inflate the layout for this fragment
        view.buttonSearchPlanet.setOnClickListener {
            (activity as MainActivity).verifyAndConnect("https://api.le-systeme-solaire.net/rest/bodies/${editTextPlanet.text}")
        }
        return view
    }


}
