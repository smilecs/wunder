package com.smile.wundercar.ui

import android.arch.lifecycle.ViewModel
import com.smile.wundercar.repo.VehicleRepository

class VehicleViewModel : ViewModel() {
    private val vehicleRepository = VehicleRepository()

    fun getVehicles() = vehicleRepository.getVehicleData()
}