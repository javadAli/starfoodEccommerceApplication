package com.example.starfood.datamodel.logindatamodel

data class LoginResponse(
    val browser: String,
    val countBuy: String,
    val loginInfo: List<LoginInfo>?,
    val loginFlag:String,
    val message: String,
    val psn: String,
    val role: String,
    val status: Int,
    val token: String,
    val username: String
)