package com.example.starfood.datamodel.repository.profilerepo

import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Single

class ProfileRemoteDataSource(val apiService: ApiService):ProfileDataSource {
    override fun getProfile(psn: String): Single<ProfileDataModel> = apiService.getProfile(
        "Bearer "+TokenContainer.token!!,psn)
    override fun getFactorPaymentFormApi(allMoney: String,psn: String): Single<String> = apiService.getFactorPaymentFormApi("Bearer "+TokenContainer.token!!,allMoney,psn)

    override fun getFactorView(psn: String, factorSn: String): Single<FactorViewDataModel> = apiService.getFactorView("Bearer "+TokenContainer.token!!,psn,factorSn)
}