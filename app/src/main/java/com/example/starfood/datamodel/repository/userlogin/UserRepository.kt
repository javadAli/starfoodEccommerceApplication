package com.example.starfood.datamodel.repository.userlogin

import com.example.starfood.datamodel.logindatamodel.LoginResponse
import io.reactivex.Single

interface UserRepository {
    fun getLoginInfo(email:String,password:String):Single<LoginResponse>
    fun loadToken()
    fun loadUserName()
    fun loadPsn()
    fun signOut()
    fun onSuccessfulLogin(username: String, tokenResponse: LoginResponse, psn:String)
}