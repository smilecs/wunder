package com.smile.wundercar.repo

import android.arch.lifecycle.LiveData
import com.past3.ketro.model.Wrapper
import com.smile.wundercar.model.Vehicle

interface VehicleDataSource {
    fun getVehicleData(): LiveData<Wrapper<List<Vehicle>>>

    fun addVehicles(vehicle:List<Vehicle>)
}