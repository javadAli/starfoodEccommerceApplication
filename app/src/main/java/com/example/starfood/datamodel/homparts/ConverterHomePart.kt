package com.example.starfood.datamodel.homparts

import androidx.room.TypeConverter
import com.example.starfood.datamodel.productexplaindatamodel.AssameKalaX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterHomePart{
    @TypeConverter
    fun fromStatesHolder(value:List<Part>): String{
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<Part>{
        val type = object: TypeToken<List<Part>>(){}.type
        return Gson().fromJson(data,type)
    }


}