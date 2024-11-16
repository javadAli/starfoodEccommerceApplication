package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

data class SubProductCategoryDataModel(
    var currency: Int,
    var currencyName: String,
    var listGroups: List<HeaderDetailCategoryModelItem>,
    var listKala: List<KalaSubGroup>,
    var logoPos: String,
    var maxSale:String,
    var mainGrId: String
)