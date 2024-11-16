package com.example.starfood.datamodel.categorydatamodel

data class HomeBrandAllKalaDataModel(
    val currency: String,
    val currencyName: String,
    val kala: List<BrandKala>,
    val logoPos: String
)