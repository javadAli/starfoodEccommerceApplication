package com.example.starfood.datamodel.profile

data class ProfileDataModel(
    val checkOfficialExistance: Int,
    val currency: String,
    val currencyName: String,
    val exacHaqiqi: ExacHaqiqi,
    val exactHoqoqi: List<ExactHoqoqi>,
    val factors: List<Factor>,
    val officialstate: Int,
    val orders: List<Order>,
    val profile: Profile
)