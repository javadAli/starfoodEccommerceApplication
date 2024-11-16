package com.example.starfood.datamodel.repository.slider.walletrepo

import com.example.starfood.datamodel.wallet.SendWalletFormDataModel
import com.example.starfood.datamodel.wallet.WalletDataModel
import io.reactivex.Single

interface WalletDataSource {
    fun wallet(psn:String): Single<WalletDataModel>
    fun sendForm(psn:String,takhfif:String,answer1:String,answer2:String,answer3:String,nazarId:String):Single<SendWalletFormDataModel>
}