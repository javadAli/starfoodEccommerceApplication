package com.example.starfood.datamodel.productexplaindatamodel

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterAsSameKalaDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<AssameKalaX>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<AssameKalaX>{
        val type = object: TypeToken<List<AssameKalaX>>(){}.type
        return Gson().fromJson(data,type)
    }


}