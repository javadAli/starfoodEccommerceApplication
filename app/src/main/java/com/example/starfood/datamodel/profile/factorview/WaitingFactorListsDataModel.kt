package com.example.starfood.datamodel.profile.factorview

data class WaitingFactorListsDataModel(
    val currency: String,
    val currencyName: String,
    val orderSn: String,
    val orders: List<Order>,
    val payedMoney: String
)