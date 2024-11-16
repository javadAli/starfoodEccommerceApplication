package com.example.starfood.datamodel.cartitem

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("order")
data class CartOrder(
    var Amount: String?,
    var AmountExist: String?,
    var CompanyNo: String?,
    var DateOrder: String?,
    var Fi: String?,
    var GoodName: String?,
    @PrimaryKey(autoGenerate = false)
    var GoodSn: String,
    var PackAmount: String?,
    var Price: String?,
    var SecondUnitAmount: String?,
    var Price1: String?,
    var Price3: String?,
    var Price4: String?,
    var SnGood: String?,
    var SnHDS: String?,
    var SnOrderBYS: String?,
    var UName: String?,
    var changedPrice: String?,
    var maxSale:String,
    var freeExistance: String?,
    var secondUnitName: String?
)