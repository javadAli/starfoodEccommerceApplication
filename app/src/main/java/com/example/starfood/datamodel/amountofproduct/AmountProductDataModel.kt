package com.example.starfood.datamodel.amountofproduct

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("amountProduct_tbl")
data class AmountProductDataModel(
    val amountExist: String,
    val amountUnit: String,
    val costError: String,
    val costLimit: String,
    val defaultUnit: String,
    val freeExistance: String,
    @PrimaryKey(autoGenerate = false)
    val kalaId: String,
    val maxSale: String,
    val minSaleAmount: String,
    val secondUnit: String,
    val zeroExistance: String
)