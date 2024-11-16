package com.example.starfood.datamodel.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity("favourite_tb")
data class FavouriteDataModel(
    @PrimaryKey(autoGenerate = false)
    val currency: Int,
    val currencyName: String,
    @TypeConverters(FavouriteConverterDataModel::class)
    val favorits: List<Favorit>,
    val logoPos: String
)