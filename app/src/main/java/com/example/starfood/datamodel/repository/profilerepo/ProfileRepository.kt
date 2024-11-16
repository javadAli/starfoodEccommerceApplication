package com.example.starfood.datamodel.repository.profilerepo
import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import io.reactivex.Single
interface ProfileRepository {
    fun getProfile(psn:String):Single<ProfileDataModel>
    fun getFactorPaymentFormApi(allMoney:String,psn: String):Single<String>
    fun getFactorView(psn:String,factorSn:String):Single<FactorViewDataModel>
}