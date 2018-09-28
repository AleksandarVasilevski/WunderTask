package com.aleksandar.wunder.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksandar.wunder.App
import com.aleksandar.wunder.R
import com.aleksandar.wunder.vo.Placemark
import com.aleksandar.wunder.vo.Placemarks
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class MapFragment : Fragment(), OnMapReadyCallback
{

    val TAG = "MAP_FRAGMENT"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private var map: GoogleMap? = null
    private lateinit var markers : ArrayList<Marker>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context!!.applicationContext as App).appComponent!!.inject(this)

        mainViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)

        if (activity != null) {
            initMap()
        }

        mainViewModel.getPlacemark().observe(this, object : Observer<Placemark?>{
            override fun onChanged(placemark: Placemark?) {
                goTo(placemark)
            }
        })
    }

    /**
     * Initializing Google Map
     */
    private fun initMap(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_googlemaps) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /**
     * Google Map callback for onMapReady
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        observePlacemarks()
    }

    /**
     * Observing the placemarks LiveData in the MainViewModel
     * and loops over the result to add all markers on the map
     * and in the arrayList
     */
    private fun observePlacemarks(){
        mainViewModel.getPlacemarks().observe(this, object : Observer<Placemarks> {
            override fun onChanged(placemarks: Placemarks?) {
                markers = ArrayList()
                var i = 0
                while (i < placemarks!!.placemark.size){
                    val latLng = LatLng(placemarks.placemark.get(i).coordinates.get(1), placemarks.placemark.get(i).coordinates.get(0))
                    val marker = map!!.addMarker(MarkerOptions().position(latLng).title(placemarks.placemark.get(i).name))
                    markers.add(marker)
                    i++
                }
            }
        })
    }

    /**
     * GoTo selected position from the InfoFragment to
     * the Marker on the MapFragment
     */
    private fun goTo(placemark: Placemark?){
        for (marker in markers){
            if (marker.title == placemark!!.name){
                map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 17F))
                marker.showInfoWindow()
            }
        }
        Log.d(TAG, placemark.toString())
    }
}
