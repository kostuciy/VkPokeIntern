package com.example.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun jsonFromStringList(list: List<String>): String =
        Gson().toJson(list)

    @TypeConverter
    fun listStringFromJson(json: String): List<String> =
        Gson().fromJson(
            json,
            object : TypeToken<List<String>>() {}.type
        )
    @TypeConverter
    fun jsonFromStringStringMap(map: Map<String, String>): String =
        Gson().toJson(map)

    @TypeConverter
    fun jsonFromStringIntMap(map: Map<String, Int>): String =
        Gson().toJson(map)

    @TypeConverter
    fun mapStringStringFromJson(json: String): Map<String, String> =
        Gson().fromJson(
            json,
            object : TypeToken<Map<String, String>>() {}.type
        )

    @TypeConverter
    fun mapStringIntFromJson(json: String): Map<String, Int> =
        Gson().fromJson(
            json,
            object : TypeToken<Map<String, String>>() {}.type
        )
}