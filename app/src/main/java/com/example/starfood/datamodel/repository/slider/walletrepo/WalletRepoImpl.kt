package com.example.starfood.datamodel.repository.slider.walletrepo

import com.example.starfood.datamodel.wallet.SendWalletFormDataModel
import com.example.starfood.datamodel.wallet.WalletDataModel
import io.reactivex.Single

class WalletRepoImpl(private val remoteDataSource: WalletRemoteDataSource, private val localDataSource: WalletLocalDataSource) :
    WalletRepository {
    override fun wallet(psn: String): Single<WalletDataModel> = remoteDataSource.wallet(psn)
    override fun sendForm(
        psn: String,
        takhfif: String,
        answer1: String,
        answer2: String,
        answer3: String,
        nazarId: String): Single<SendWalletFormDataModel> = remoteDataSource.sendForm(psn,takhfif,answer1,answer2,answer3,nazarId)
}