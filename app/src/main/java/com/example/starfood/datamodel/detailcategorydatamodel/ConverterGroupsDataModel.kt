package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//@ProvidedTypeConverter
class ConverterGroupsDataModel {
    private val  gson =Gson()
    @TypeConverter
    fun fromStatesHolder(value:List<Groups>): String{
        //val type = object: TypeToken<List<Order>>(){}.type
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<Groups>{
        val type = object: TypeToken<List<Groups>>(){}.type
        return Gson().fromJson(data,type)
    }


}