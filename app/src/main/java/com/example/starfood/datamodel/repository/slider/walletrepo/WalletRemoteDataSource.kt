package com.example.starfood.datamodel.repository.slider.walletrepo

import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.wallet.SendWalletFormDataModel
import com.example.starfood.datamodel.wallet.WalletDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Single

class WalletRemoteDataSource(val apiService: ApiService): WalletDataSource {
    override fun wallet(psn: String): Single<WalletDataModel> = apiService.wallet("Bearer "+TokenContainer.token!!,psn)
    override fun sendForm(
        psn: String,
        takhfif: String,
        answer1: String,
        answer2: String,
        answer3: String,
        nazarId: String): Single<SendWalletFormDataModel> = apiService.sendWalletForm("Bearer "+TokenContainer.token!!,psn,takhfif,answer1,answer2,answer3,nazarId)
}