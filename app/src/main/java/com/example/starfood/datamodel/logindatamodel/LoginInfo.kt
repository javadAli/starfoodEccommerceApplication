package com.example.starfood.datamodel.logindatamodel

data class LoginInfo(
    val browser: String,
    val customerId: String,
    val id: String,
    val mobileToken: String,
    val platform: String,
    val sessionId: String
)