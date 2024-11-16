package com.example.starfood.datamodel.repository.slider.walletrepo

import com.example.starfood.datamodel.profit.ProfitDataModel
import com.example.starfood.datamodel.wallet.SendWalletFormDataModel
import com.example.starfood.datamodel.wallet.WalletDataModel
import io.reactivex.Single

class WalletLocalDataSource: WalletDataSource {

    override fun wallet(psn: String): Single<WalletDataModel> {
        TODO("Not yet implemented")
    }

    override fun sendForm(
        psn: String,
        takhfif: String,
        answer1: String,
        answer2: String,
        answer3: String,
        nazarId: String
    ): Single<SendWalletFormDataModel> {
        TODO("Not yet implemented")
    }
}