package com.example.starfood.datamodel.detailcategorydatamodel.appendkala

import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup

data class AppendSubKalaDataModel(
    val currency: String,
    val currencyName: String,
    val listGroups: List<Groups>,
    val listKala: List<KalaSubGroup>,
    val logoPos: String,
    val mainGrId: String,
    val maxSale:String,
    val subGroupId: String
)