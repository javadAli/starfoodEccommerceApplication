package com.example.starfood.datamodel.categorydatamodel


import com.google.gson.annotations.SerializedName

data class HomePartListKalaOfPictureDataModel(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currencyName")
    val currencyName: String,
    @SerializedName("homePartId")
    val homePartId: String,
    @SerializedName("logoPos")
    val logoPos: String,
    @SerializedName("picId")
    val picId: String,
    @SerializedName("kala")
    val kala: List<Kala>
)