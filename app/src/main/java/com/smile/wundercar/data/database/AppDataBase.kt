package com.smile.wundercar.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.smile.wundercar.SingletonHolder
import com.smile.wundercar.model.ArrayToString
import com.smile.wundercar.model.Vehicle
import com.smile.wundercar.repo.dao.VehicleDao

@Database(entities = [Vehicle::class], version = 1)
@TypeConverters(ArrayToString.DoubleRoomTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao

    companion object : SingletonHolder<AppDataBase, Context>({
        Room.databaseBuilder(it.applicationContext,
                AppDataBase::class.java, "Vehicle_db")
                .build()
    })
}