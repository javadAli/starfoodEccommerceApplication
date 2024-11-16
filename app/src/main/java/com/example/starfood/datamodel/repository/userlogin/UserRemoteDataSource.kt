package com.example.starfood.datamodel.repository.userlogin

import com.example.starfood.datamodel.logindatamodel.LoginResponse
import com.example.starfood.service.ApiService
import io.reactivex.Single

class UserRemoteDataSource(private val apiService: ApiService) : UserDataSource {
    override fun getLoginInfo(email: String, password: String): Single<LoginResponse> = apiService.loginInfo(email,password)

    override fun loadToken() {
        TODO("Not yet implemented")
    }

    override fun loadPsn() {
        TODO("Not yet implemented")
    }

    override fun loadUserName() {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun saveUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override fun savePsn(psn: String) {
        TODO("Not yet implemented")
    }

}