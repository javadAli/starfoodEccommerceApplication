package com.example.starfood.datamodel.repository.userlogin

import android.content.SharedPreferences
import com.example.starfood.datamodel.logindatamodel.LoginResponse
import io.reactivex.Single

class UserLocalDataSource(private val sharedPreferences: SharedPreferences): UserDataSource {


    override fun getLoginInfo(email: String, password: String): Single<LoginResponse> {
        TODO("Not yet implemented")
    }

    override fun loadToken() {
        TokenContainer.tokenUpdate(sharedPreferences.getString("token",null))
    }

    override fun loadPsn() {
        TokenContainer.psnUpdate(sharedPreferences.getString("psn",null))

    }

    override fun loadUserName() {
        TokenContainer.userNameUpdate(sharedPreferences.getString("userName",null))

    }

    override fun saveToken(token: String) {
       sharedPreferences.edit().apply{
           putString("token",token)
       }.apply()
    }

    override fun signOut() {
        sharedPreferences.edit().apply {
            clear()
        }.apply()
    }

    override fun savePsn(psn: String) {
        sharedPreferences.edit().apply{
            putString("psn",psn)
        }.apply()
    }

    override fun saveUsername(username: String) {
        sharedPreferences.edit().apply{
            putString("userName",username)
        }.apply()
    }


}