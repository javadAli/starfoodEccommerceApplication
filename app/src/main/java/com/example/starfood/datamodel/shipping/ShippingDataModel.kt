package com.example.starfood.datamodel.shipping

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.starfood.datamodel.cartitem.ConverterStringDataModel

@Entity("shipping_tbl")
data class ShippingDataModel(
    @TypeConverters(ShippingAddressConverter::class)
    val addresses: List<Addresse>,
    val afterTomorrowDate: String,
    val allMoney: Int,
    val currency: String,
    val currencyName: String,
    @PrimaryKey(autoGenerate = false)
    @TypeConverters(ShippingCustomerConverter::class)
    val customer: Customer,
    val date1: String,
    val date2: String,
    val pardakhtLive: String,
    val profit: Int,
    @TypeConverters(ShippingSettingConverter::class)
    val setting: Setting,
    val takhfifCase: Int,
    val tomorrowDate: String
)