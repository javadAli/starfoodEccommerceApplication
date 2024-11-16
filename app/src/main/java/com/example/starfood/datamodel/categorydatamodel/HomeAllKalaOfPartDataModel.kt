package com.example.starfood.datamodel.categorydatamodel

data class HomeAllKalaOfPartDataModel(
    val currency: String,
    val currencyName: String,
    val kala: List<KalaShowAll>,
    val logoPos: String
)