package com.smile.wundercar.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

@Entity
data class Vehicle(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        val address: String,
        @TypeConverters(ArrayToString.DoubleRoomTypeConverter::class)
        val coordinates: List<Double>,
        val engineType: String,
        val exterior: String,
        val fuel: Double,
        val interior: String,
        val name: String,
        val vin: String)

/*
{
            "address": "Ballindamm 25, 20095 Hamburg",
            "coordinates": [
                9.99805,
                53.55384,
                0
            ],
            "engineType": "CE",
            "exterior": "UNACCEPTABLE",
            "fuel": 24,
            "interior": "UNACCEPTABLE",
            "name": "HH-GO8444",
            "vin": "WME4513341K566975"
        }
 */