package com.example.starfood.datamodel.cartitem

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity("cart_tbl")
data class CartDataModel(
    var changedPriceState: String,
    var currency: Int,
    var currencyName: String,
    @PrimaryKey(autoGenerate = false)
    var intervalBetweenBuys: String,
    var logoPos: String,
    var minSalePriceFactor: String,
    @TypeConverters(ConverterStringDataModel::class)
    var orderPishKarids: List<String>,
    @TypeConverters(ConverterOrderDataModel::class)
    var orders:List<CartOrder>
)