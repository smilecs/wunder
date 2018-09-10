package com.smile.wundercar.repo.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.smile.wundercar.model.Vehicle

@Dao
interface VehicleDao {
    @Query("SELECT * FROM Vehicle")
    fun getAllVehicles(): LiveData<List<Vehicle>>

    @Insert
    fun addVehicles(vehicle: List<Vehicle>)

    @Query("DELETE FROM Vehicle")
    fun nukeTable()
}