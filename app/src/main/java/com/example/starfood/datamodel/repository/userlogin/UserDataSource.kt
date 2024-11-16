package com.example.starfood.datamodel.repository.userlogin

import com.example.starfood.datamodel.logindatamodel.LoginResponse
import io.reactivex.Single

interface UserDataSource {
    fun getLoginInfo(email:String, password:String): Single<LoginResponse>
    fun loadToken()
    fun loadPsn()
    fun loadUserName()
    fun saveToken(token: String)
    fun saveUsername(username: String)
    fun signOut()
    fun savePsn(psn: String)

}