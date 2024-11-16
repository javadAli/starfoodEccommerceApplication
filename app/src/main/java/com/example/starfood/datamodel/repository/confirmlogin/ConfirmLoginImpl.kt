package com.example.starfood.datamodel.repository.confirmlogin

import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import io.reactivex.Single

class ConfirmLoginImpl(private val confirmRemoteDataSource: ConfirmRemoteDataSource, private val confirmLocalDataSource: ConfirmLocalDataSource) : ConfirmLoginRepository{
    override fun getConfirm(customerId: String, token: String): Single<ConfirmLoginDataModel> {
       return confirmRemoteDataSource.getConfirm(customerId,token)
    }
}