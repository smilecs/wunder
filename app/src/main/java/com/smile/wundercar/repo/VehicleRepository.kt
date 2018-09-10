package com.smile.wundercar.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.past3.ketro.model.Wrapper
import com.smile.wundercar.App
import com.smile.wundercar.data.database.AppDataBase
import com.smile.wundercar.model.Vehicle
import com.smile.wundercar.repo.api.request.VehicleReq
import com.smile.wundercar.repo.dao.VehicleDao
import org.jetbrains.anko.doAsync

class VehicleRepository : VehicleDataSource {

    private val vehicleDao: VehicleDao by lazy {
        AppDataBase.getInstance(App.getsInstance()).vehicleDao()
    }
    private val mediatorLiveData = MediatorLiveData<Wrapper<List<Vehicle>>>()

    init {
        vehicleAggregator()
    }

    override fun getVehicleData(): LiveData<Wrapper<List<Vehicle>>> {
        return mediatorLiveData
    }

    private fun vehicleAggregator() {
        mediatorLiveData.addSource(getLocalData()) {
            if (it != null && !it.isEmpty()) {
                val wrapper = Wrapper<List<Vehicle>>()
                wrapper.data = it
                mediatorLiveData.value = wrapper
            } else {
                getRemoteData()
            }
        }
    }

    private fun getLocalData(): LiveData<List<Vehicle>> {
        return vehicleDao.getAllVehicles()
    }

    private fun getRemoteData() {
        val vehicleSource = VehicleReq().doRequest()
        mediatorLiveData.addSource(vehicleSource) { value ->
            val wrapper = Wrapper<List<Vehicle>>()
            if (value?.data != null) {
                wrapper.data = value.data?.placeMarks
                doAsync {
                    addVehicles(wrapper.data!!)
                }
            } else {
                wrapper.exception = value?.exception
            }
            mediatorLiveData.removeSource(vehicleSource)
            mediatorLiveData.value = wrapper
        }
    }

    override fun addVehicles(vehicle: List<Vehicle>) {
        vehicleDao.nukeTable()
        vehicleDao.addVehicles(vehicle)
    }
}