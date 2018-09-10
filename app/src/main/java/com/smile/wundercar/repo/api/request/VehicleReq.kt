package com.smile.wundercar.repo.api.request

import com.past3.ketro.api.ApiErrorHandler
import com.past3.ketro.api.GenericRequestHandler
import com.smile.wundercar.data.net.NetModule
import com.smile.wundercar.model.Vehicle
import com.smile.wundercar.repo.api.error.ErrorHandler
import com.smile.wundercar.repo.api.response.VehicleResponse
import retrofit2.Call

class VehicleReq : GenericRequestHandler<VehicleResponse>() {

    fun makeVehicleRequest(): List<Vehicle> {
        val req = NetModule.getVehicleService().getVehicles().execute()
        with(req) {
            if (isSuccessful && body() != null) {
                return body()?.placeMarks!!
            } else {
                throw IllegalArgumentException()
            }
        }
    }

    override fun makeRequest(): Call<VehicleResponse> {
        return NetModule.getVehicleService().getVehicles()
    }

    override val errorHandler: ApiErrorHandler = ErrorHandler()
}