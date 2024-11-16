package com.example.starfood.datamodel.detailcategorydatamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("productDetailHeader_tbl")
data class HeaderDetailCategoryModelItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val selfGroupId: String,
    val title: String
)