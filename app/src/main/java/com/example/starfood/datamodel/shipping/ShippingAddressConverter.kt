package com.example.starfood.datamodel.shipping

import androidx.room.TypeConverter
import com.example.starfood.datamodel.productexplaindatamodel.AssameKalaX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShippingAddressConverter{
    @TypeConverter
    fun fromStatesHolder(value:List<Addresse>): String{
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): List<Addresse>{
        val type = object: TypeToken<List<Addresse>>(){}.type
        return Gson().fromJson(data,type)
    }


}