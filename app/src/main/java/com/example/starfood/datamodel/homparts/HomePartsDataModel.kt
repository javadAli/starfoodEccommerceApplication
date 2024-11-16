package com.example.starfood.datamodel.homparts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("HomeParts_tbl")
data class HomePartsDataModel(
    @PrimaryKey(autoGenerate = false)
    val parts: List<Part>,
    val showEnamad: String
)