package com.smile.wundercar

import android.Manifest
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.PermissionChecker
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

class LocationListener(private val context: Context) : LiveData<Location>(), GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private var googleClient: GoogleApiClient = GoogleApiClient.Builder(context, this, this)
            .addApi(LocationServices.API)
            .build()


    override fun onConnected(p0: Bundle?) {
        if (PermissionChecker.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (hasActiveObservers()) {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleClient, LocationRequest(), this)
            }
            value = LocationServices.FusedLocationApi.getLastLocation(googleClient)
        }
    }

    override fun onActive() {
        googleClient.connect()
    }

    override fun onInactive() {
        if (googleClient.isConnected) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleClient, this)
        }
        googleClient.disconnect()
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            value = it
        }
    }
}