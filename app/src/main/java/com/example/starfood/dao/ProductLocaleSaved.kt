package com.example.starfood.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("productLocaleSaved_tbl")
data class ProductLocaleSaved(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    val goodSn:Long,
    val requested:Boolean,
    val cancelRequested:Boolean,
    val favourite:Boolean,
    val isBuy:Boolean,
    val amountBuy:String?,
    val snOrder:String?,
    val isFirst:Boolean
)