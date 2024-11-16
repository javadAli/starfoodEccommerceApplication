package com.example.starfood.datamodel.addfactor

data class AddFactorDataModel(
    val currency: String,
    val currencyName: String,
    val orderBYS: List<OrderBYS>,
    val orderNo: Int,
    val profit: String
)