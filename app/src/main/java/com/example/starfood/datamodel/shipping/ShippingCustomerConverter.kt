package com.example.starfood.datamodel.shipping

import androidx.room.TypeConverter
import com.example.starfood.datamodel.productexplaindatamodel.AssameKalaX
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShippingCustomerConverter{
    @TypeConverter
    fun fromStatesHolder(value:Customer): String{
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStatesHolder(data:String): Customer{
        val type = object: TypeToken<Customer>(){}.type
        return Gson().fromJson(data,type)
    }


}