package com.example.starfood.datamodel.repository.confirmlogin

import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Single

class ConfirmRemoteDataSource(val apiService: ApiService):ConfirmDataSource {
    override fun getConfirm(customerId: String, token: String): Single<ConfirmLoginDataModel> = apiService.logOutConfirm(customerId,TokenContainer.sessionId!!,TokenContainer.token!!,"Android","Moblie App","device token")
}