package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity("subProduct_tbl",primaryKeys = ["GoodSn", "mainGrId"])
data class SubProductTable(
    var currency: Int,
    var currencyName: String,
    var Amount: String?,
    var BoughtAmount: String?,
    var GoodName: String?,
    var GoodSn: String,
    var PackAmount: String?,
    var Price3: String?,
    var Price4: String?,
    var SnGoodPriceSale: String?,
    var SnOrderBYS: String?,
    var UName: String?,
    var activePishKharid: String?,
    var activeTakhfifPercent: String?,
    var bought: String?,
    var callOnSale: String?,
    var favorite: String?,
    var firstGroupId: String?,
    var freeExistance: String?,
    var overLine: String?,
    var requested: String?,
    var secondGroupId: String?,
    var secondUnit: String?,
    var SecondUnitAmount: String?,
    var logoPos: String,
    var mainGrId: String
)