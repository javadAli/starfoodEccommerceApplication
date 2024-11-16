package com.example.starfood.datamodel.slider

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class ConverterSliderDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<Slider>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<Slider>{
        val type = object: TypeToken<List<Slider>>(){}.type
        return Gson().fromJson(data,type)
    }
}