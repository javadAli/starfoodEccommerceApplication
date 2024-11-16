package com.example.starfood.datamodel.categorydatamodel


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
    val brandId: Any,
    @SerializedName("callOnSale")
    val callOnSale: Int,
    @SerializedName("favorite")
    val favorite: String,
    @SerializedName("fifthPic")
    val fifthPic: Any,
    @SerializedName("firstGroupId")
    val firstGroupId: Any,
    @SerializedName("firstGroupSn")
    val firstGroupSn: String,
    @SerializedName("firstPic")
    val firstPic: Any,
    @SerializedName("firstUnit")
    val firstUnit: String,
    @SerializedName("fourthPic")
    val fourthPic: Any,
    @SerializedName("freeExistance")
    val freeExistance: Int,
    @SerializedName("GoodGroupSn")
    val goodGroupSn: String,
    @SerializedName("GoodName")
    val goodName: String,
    @SerializedName("GoodSn")
    val goodSn: String,
    @SerializedName("homepartId")
    val homepartId: Any,
    @SerializedName("id")
    val id: String,
    @SerializedName("isVisible")
    val isVisible: Any,
    @SerializedName("itsHomePartId")
    val itsHomePartId: String,
    @SerializedName("maxSale")
    val maxSale: String,
    @SerializedName("overLine")
    val overLine: Int,
    @SerializedName("partPic")
    val partPic: String,
    @SerializedName("preBought")
    val preBought: String,
    @SerializedName("preBoughtAmount")
    val preBoughtAmount: Int,
    @SerializedName("preBoughtPackAmount")
    val preBoughtPackAmount: Int,
    @SerializedName("preBoughtSnOrderBYS")
    val preBoughtSnOrderBYS: Any,
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
    @SerializedName("secondGroupId")
    val secondGroupId: Any,
    @SerializedName("secondPic")
    val secondPic: Any,
    @SerializedName("secondUnit")
    val secondUnit: String,
    @SerializedName("SecondUnitAmount")
    val secondUnitAmount: String,
    @SerializedName("SnOrderBYS")
    val snOrderBYS: Any,
    @SerializedName("thirdGroupId")
    val thirdGroupId: Any,
    @SerializedName("thirdPic")
    val thirdPic: Any,
    @SerializedName("UName")
    val uName: String
)