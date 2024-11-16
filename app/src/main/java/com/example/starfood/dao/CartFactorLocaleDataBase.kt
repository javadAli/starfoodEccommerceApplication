package com.example.starfood.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("cartLocale_tbl")
data class CartFactorLocaleDataBase(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    val total:String,
    val customerAddress:String,
    val recivedTime:String,
    val profit:String
)