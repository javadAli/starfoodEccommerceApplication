package com.example.starfood.common

import androidx.annotation.StringRes

class StarFoodException(
    val type: Type, @StringRes val userFriendlyMessage: Int = 0,
    val serverMessage: String? = null
) : Throwable() {

    enum class Type {
        SIMPLE, DIALOG, AUTH
    }
}