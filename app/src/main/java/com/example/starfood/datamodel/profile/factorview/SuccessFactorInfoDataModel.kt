package com.example.starfood.datamodel.profile.factorview

data class SuccessFactorInfoDataModel(
    val currency: String,
    val currencyName: String,
    val factorBYS: List<SuccessInfoFactorBYS>,
    val factorNo: String
)