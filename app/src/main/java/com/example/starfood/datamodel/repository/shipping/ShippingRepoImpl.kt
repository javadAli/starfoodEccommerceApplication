package com.example.starfood.datamodel.repository.shipping

import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.CartFactorLocaleDataBase
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.applydiscount.ApplyDiscount
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import io.reactivex.Completable
import io.reactivex.Single

class ShippingRepoImpl(private val remoteDataSource: ShippingRemoteDataSource, private val localDataSource: ShippingLocalDataSource) : ShippingRepository{
    override fun shippingData(psn: String, allMoney:Long,profit:Long): Single<ShippingDataModel> =if (NetworkStateHolder.isConnected) remoteDataSource.shippingData(psn,allMoney,profit)
    else localDataSource.getLocaleShippingData()

    override fun saveFactorsToLocale(item: CartFactorLocaleDataBase): Completable = localDataSource.saveFactorsToLocale(item)

    override fun getLocaleShippingData(): Single<ShippingDataModel> = localDataSource.getLocaleShippingData()

    override fun addShippingDataToLocale(item: ShippingDataModel): Completable = localDataSource.addShippingDataToLocale(item)

    override fun checkTakhfifCode(psn: String, code: String): Single<ApplyDiscount> = remoteDataSource.checkTakhfifCode(psn,code)

    override fun addFactor(
        psn: String,
        allMoney: Long,
        customerAddress: String,
        recivedTime: String,profit:Long
    ): Single<AddFactorDataModel> = remoteDataSource.addFactor(psn,allMoney,customerAddress,recivedTime,profit)

    override fun getPaymentForm(psn: String, allMoney: Long): Single<String> = remoteDataSource.getPaymentForm(psn,allMoney)
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
    ): Single<SuccessPayOnlineDataModel> = remoteDataSource.successOnlinePay(psn,allMoney,tref,iN,iD,takhfif,takhfifCode,recivedTime,receviedAddress)
    override fun successOnlineFactorPay(
        psn: String,
        allMoney: String,
        factprSn: String,
        tref: String,
        iN: String,
        iD: String
    ): Single<SuccessPayOnlineDataModel> = remoteDataSource.successOnlineFactorPay(psn,allMoney,factprSn,tref,iN,iD)
}