package com.example.starfood.datamodel.brandParts


import com.google.gson.annotations.SerializedName

data class Kala(
    @SerializedName("activePishKharid")
    val activePishKharid: Int,
    @SerializedName("activeTakhfifPercent")
    val activeTakhfifPercent: String,
    @SerializedName("Amount")
    val amount: String,
    @SerializedName("bought")
    val bought: String,
    @SerializedName("BoughtAmount")
    val boughtAmount: Int,
    @SerializedName("BoughtPackAmount")
    val boughtPackAmount: Int,
    @SerializedName("brandId")
    val brandId: String,
    @SerializedName("callOnSale")
    val callOnSale: Int,
    @SerializedName("favorite")
    val favorite: String,
    @SerializedName("firstGroupId")
    val firstGroupId: String,
    @SerializedName("firstUnit")
    val firstUnit: String,
    @SerializedName("freeExistance")
    val freeExistance: String,
    @SerializedName("GoodName")
    val goodName: String,
    @SerializedName("GoodSn")
    val goodSn: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("maxSale")
    val maxSale: String,
    @SerializedName("overLine")
    val overLine: Int,
    @SerializedName("Price3")
    val price3: String,
    @SerializedName("Price4")
    val price4: String,
    @SerializedName("priority")
    val priority: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("requested")
    val requested: Int,
    @SerializedName("secondUnit")
    val secondUnit: String,
    @SerializedName("SecondUnitAmount")
    val secondUnitAmount: String,
    @SerializedName("SnOrderBYS")
    val snOrderBYS: Any
)