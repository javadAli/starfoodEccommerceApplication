package com.example.starfood.datamodel.productexplaindatamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("ProductExplain_tbl")
data class ProductExplainDataModel(
    var Amount: Int,
    var AmountExist: String,
    var FiscalYear: String,
    var GoodCde: String,
    var GoodName: String,
    @PrimaryKey(autoGenerate = false)
    var GoodSn: String,
    var NameGRP: String,
    var SecondUnitAmount: String,
    var PackAmount: Int,
    var Price3: String,
    var Price4: String,
    var SnOrderBYS: Int,
    var UNAME: String,
    var activePishKharid: String,
    var activeTakhfifPercent: String,
    var assameKala: List<AssameKalaX>,
    var bought: String,
    var callOnSale: Int,
    var currency: String,
    var currencyName: String,
    var descKala: String,
    var favorite: String,
    var freeExistance: String,
    var groupName: String,
    var logoPos: String,
    var mainGroupId: String,
    val maxSale:String,
    var overLine: Int,
    var preBought: String,
    var requested: Int,
    var secondUnit: String
)