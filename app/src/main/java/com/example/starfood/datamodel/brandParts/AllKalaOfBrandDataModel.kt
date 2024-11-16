package com.example.starfood.datamodel.brandParts


import com.google.gson.annotations.SerializedName

data class AllKalaOfBrandDataModel(
    @SerializedName("brandId")
    val brandId: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currencyName")
    val currencyName: String,
    @SerializedName("kala")
    val kala: List<Kala>,
    @SerializedName("logoPos")
    val logoPos: String
)