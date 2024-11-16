package com.example.starfood.datamodel.repository.confirmlogin

import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import io.reactivex.Single

class ConfirmLocalDataSource:ConfirmDataSource {
    override fun getConfirm(customerId: String, token: String): Single<ConfirmLoginDataModel> {
        TODO("Not yet implemented")
    }
}