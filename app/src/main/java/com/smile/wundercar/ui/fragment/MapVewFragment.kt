package com.smile.wundercar.ui.fragment


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.past3.ketro.api.Kobserver
import com.smile.wundercar.R
import com.smile.wundercar.model.Vehicle
import com.smile.wundercar.ui.VehicleViewModel
import kotlinx.android.synthetic.main.fragment_map_view.*


class MapVewFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var markerList: List<Marker>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapview.onCreate(savedInstanceState)
        mapview.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            it.setOnMarkerClickListener(this)
            initVehicleData(it)
        }
    }

    private fun initVehicleData(map: GoogleMap) {
        val viewModel = ViewModelProviders.of(requireActivity()).get(VehicleViewModel::class.java)
        viewModel.getVehicles().observe(this, object : Kobserver<List<Vehicle>>() {
            override fun onException(exception: Exception) {
                Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(data: List<Vehicle>) {
                addVehicleMarkers(data, map)
            }
        })
    }

    private fun addVehicleMarkers(data: List<Vehicle>, map: GoogleMap) {
        lateinit var latLng: LatLng
        markerList = data.flatMap {
            latLng = LatLng(it.coordinates[1], it.coordinates[0])
            val marker = MarkerOptions().position(latLng).title(it.name)
            listOf(map.addMarker(marker))
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        marker?.let {
            toggleVisibility(it)
        }
        return true
    }

    private fun toggleVisibility(marker: Marker) {
        markerList?.forEach {
            if (marker != it) {
                it.isVisible = !it.isVisible
            } else {
                if (it.isInfoWindowShown) {
                    it.hideInfoWindow()
                } else {
                    it.showInfoWindow()
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mapview.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapview.onLowMemory()
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapview.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapview.onSaveInstanceState(outState)
    }
}
