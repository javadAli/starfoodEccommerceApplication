package com.example.starfood.datamodel.shipping

import androidx.room.TypeConverter
import com.example.starfood.datamodel.productexplaindatamodel.AssameKalaX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShippingSettingConverter{
    @TypeConverter
    fun fromStatesHolder(value:Setting): String{
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): Setting{
        val type = object: TypeToken<Setting>(){}.type
        return Gson().fromJson(data,type)
    }


}