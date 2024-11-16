package com.example.starfood.datamodel.repository.profilerepo

import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import io.reactivex.Single

class ProfileLocalDataSource:ProfileDataSource {

    override fun getProfile(psn: String): Single<ProfileDataModel> {
        TODO("Not yet implemented")
    }

    override fun getFactorPaymentFormApi(allMoney: String,psn: String): Single<String> {
        TODO("Not yet implemented")
    }

    override fun getFactorView(psn: String, factorSn: String): Single<FactorViewDataModel> {
        TODO("Not yet implemented")
    }
}