package com.example.starfood.datamodel.slider

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class ConverterSmallSliderDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<SmallSlider>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<SmallSlider>{
        val type = object: TypeToken<List<SmallSlider>>(){}.type
        return Gson().fromJson(data,type)
    }
}