package com.example.starfood.datamodel.categorydatamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_tbl")
data class CategoryDataModelItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String
)