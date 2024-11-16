package com.example.starfood.datamodel.shipping

data class SuccessPayOnlineDataModel(
    val currency: String,
    val currencyName: String,
    val factorBYS: List<FactorBYS>,
    val factorNo: Int,
    val payResults: PayResults,
    val profit: Int,
    val result: String
)