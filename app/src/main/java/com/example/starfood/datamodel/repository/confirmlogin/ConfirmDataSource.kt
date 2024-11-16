package com.example.starfood.datamodel.repository.confirmlogin

import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import io.reactivex.Single

interface ConfirmDataSource {
    fun getConfirm(customerId:String,token:String): Single<ConfirmLoginDataModel>
}