package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class ConverterKalaDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<KalaSubGroup>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<KalaSubGroup>{
        val type = object: TypeToken<List<KalaSubGroup>>(){}.type
        return Gson().fromJson(data,type)
    }


}