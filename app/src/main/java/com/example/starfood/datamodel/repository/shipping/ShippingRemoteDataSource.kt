package com.example.starfood.datamodel.repository.shipping

import com.example.starfood.dao.CartFactorLocaleDataBase
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.applydiscount.ApplyDiscount
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class ShippingRemoteDataSource(val apiService: ApiService):ShippingDataSource {
    override fun shippingData(psn: String, allMoney:Long,profit:Long): Single<ShippingDataModel> = apiService.shippingData(
        "Bearer "+TokenContainer.token!!,psn,allMoney,profit)
    override fun getLocaleShippingData(): Single<ShippingDataModel> {
        TODO("Not yet implemented")
    }

    override fun saveFactorsToLocale(item: CartFactorLocaleDataBase): Completable {
        TODO("Not yet implemented")
    }

    override fun addFactor(psn: String, allMoney: Long, customerAddress: String, recivedTime: String,profit:Long): Single<AddFactorDataModel> = apiService.addFactor("Bearer "+TokenContainer.token!!,psn,allMoney,customerAddress,recivedTime,profit)

    override fun checkTakhfifCode(psn: String, code: String): Single<ApplyDiscount> = apiService.checkTakhfifCodeReliablity("Bearer "+TokenContainer.token!!,psn,code)
    override fun getPaymentForm(psn: String, allMoney: Long): Single<String> = apiService.getPaymentForm("Bearer "+TokenContainer.token!!,psn,allMoney)
    override fun addShippingDataToLocale(item: ShippingDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun successOnlinePay(
        psn: String,
        allMoney: String,
        tref: String,
        iN: String,
        iD: String,
        takhfif: String,
        takhfifCode: String,
        recivedTime: String,
        receviedAddress: String
    ): Single<SuccessPayOnlineDataModel> = apiService.successPay("Bearer "+TokenContainer.token!!,psn,allMoney,tref,iN,iD,takhfif,takhfifCode,recivedTime,receviedAddress)
    override fun successOnlineFactorPay(
        psn: String,
        allMoney: String,
        factorSn: String,
        tref:String,
        iN: String,
        iD: String
    ): Single<SuccessPayOnlineDataModel> = apiService.finalizeFactorPayApi("Bearer "+TokenContainer.token!!,psn,allMoney,factorSn, tref,iN,iD)
}