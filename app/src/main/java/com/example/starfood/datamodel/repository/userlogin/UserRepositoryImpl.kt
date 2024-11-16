package com.example.starfood.datamodel.repository.userlogin

import com.example.starfood.datamodel.logindatamodel.LoginResponse
import io.reactivex.Single

class UserRepositoryImpl(private val userLocalDataSource: UserLocalDataSource, private val userRemoteDataSource: UserRemoteDataSource):
    UserRepository {
    override fun getLoginInfo(email: String, password: String): Single<LoginResponse> {
        return userRemoteDataSource.getLoginInfo(email, password)
    }


    override fun loadToken() {
        userLocalDataSource.loadToken()
    }

    override fun loadUserName() {
        userLocalDataSource.loadUserName()
    }

    override fun loadPsn() {
        userLocalDataSource.loadPsn()
    }

    override fun signOut() {
        userLocalDataSource.signOut()
        TokenContainer.tokenUpdate(null)
        TokenContainer.psnUpdate(null)
        TokenContainer.userNameUpdate(null)
    }

    override fun onSuccessfulLogin(username: String, tokenResponse: LoginResponse, psn:String) {
        TokenContainer.tokenUpdate(tokenResponse.token)
        TokenContainer.psnUpdate(psn)
        TokenContainer.userNameUpdate(username)
        userLocalDataSource.saveToken(tokenResponse.token)
        userLocalDataSource.saveUsername(username)
        userLocalDataSource.savePsn(psn)
    }
}