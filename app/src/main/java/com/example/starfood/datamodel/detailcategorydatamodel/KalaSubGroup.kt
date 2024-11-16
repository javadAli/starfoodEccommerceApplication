package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("kala_tbl")
data class KalaSubGroup(
    var Amount: String?,
    var BoughtAmount: String?,
    var CompanyNo: String?,
    var GoodName: String?,
    @PrimaryKey(autoGenerate = false)
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
    var maxSale:String,
    var overLine: String?,
    var requested: String?,
    var secondGroupId: String?,
    var secondUnit: String?,
    var SecondUnitAmount: String?
)