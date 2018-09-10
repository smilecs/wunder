package com.smile.wundercar.repo.api

import com.smile.wundercar.data.net.Urls
import com.smile.wundercar.repo.api.response.VehicleResponse
import retrofit2.Call
import retrofit2.http.GET

interface VehicleService {
    @GET(Urls.GET_VEHICLES)
    fun getVehicles(): Call<VehicleResponse>
}