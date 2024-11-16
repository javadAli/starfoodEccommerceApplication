package com.example.starfood.datamodel.cartitem

import androidx.room.TypeConverter
import com.google.gson.Gson

class ConverterStringDataModel {

    @TypeConverter
    fun fromStatesHolder(sh: List<String>): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toStatesHolder(sh: String): List<String> {
        return sh.split(",")
    }
}