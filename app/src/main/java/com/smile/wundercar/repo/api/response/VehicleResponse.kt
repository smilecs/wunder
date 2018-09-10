package com.smile.wundercar.repo.api.response

import com.smile.wundercar.model.Vehicle
import com.squareup.moshi.Json

data class VehicleResponse(@Json(name = "placemarks") val placeMarks: List<Vehicle>)