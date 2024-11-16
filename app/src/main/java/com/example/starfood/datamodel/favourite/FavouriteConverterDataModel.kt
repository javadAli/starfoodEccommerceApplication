package com.example.starfood.datamodel.favourite

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class FavouriteConverterDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<Favorit>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<Favorit>{
        val type = object: TypeToken<List<Favorit>>(){}.type
        return Gson().fromJson(data,type)
    }


}