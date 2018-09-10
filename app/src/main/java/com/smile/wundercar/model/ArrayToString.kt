package com.smile.wundercar.model

import android.arch.persistence.room.TypeConverter
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import kotlinx.android.parcel.Parcelize
import java.util.*

object ArrayToString {
    class DoubleRoomTypeConverter {
        private val gson = Gson()

        @TypeConverter
        fun stringToDoubleList(data: String?): List<Double> {
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<Double>>() {
            }.type

            return gson.fromJson(data, listType)
        }

        @TypeConverter
        fun DoubleListToString(someObjects: List<Double>): String {
            return gson.toJson(someObjects)
        }
    }
}
