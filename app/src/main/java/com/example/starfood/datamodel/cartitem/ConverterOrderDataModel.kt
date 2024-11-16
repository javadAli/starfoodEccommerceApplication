package com.example.starfood.datamodel.cartitem

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class ConverterOrderDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<CartOrder>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<CartOrder>{
        val type = object: TypeToken<List<CartOrder>>(){}.type
        return Gson().fromJson(data,type)
    }
}