package com.example.starfood.datamodel.slider

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Slider(
    val fifthPic: String,
    val firstPic: String,
    val fourthPic: String,
    val secondPic: String,
    val thirdPic: String
) : Parcelable