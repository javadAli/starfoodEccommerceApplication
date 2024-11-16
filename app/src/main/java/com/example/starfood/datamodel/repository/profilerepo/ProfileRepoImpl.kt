package com.example.starfood.datamodel.repository.profilerepo

import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import io.reactivex.Single

class ProfileRepoImpl(private val remoteDataSource: ProfileRemoteDataSource, private val localDataSource: ProfileLocalDataSource) : ProfileRepository{

    override fun getProfile(psn: String): Single<ProfileDataModel> = remoteDataSource.getProfile(psn)
    override fun getFactorPaymentFormApi(allMoney: String,psn: String): Single<String> = remoteDataSource.getFactorPaymentFormApi(allMoney,psn)

    override fun getFactorView(psn: String, factorSn: String): Single<FactorViewDataModel> = remoteDataSource.getFactorView(psn,factorSn)
}