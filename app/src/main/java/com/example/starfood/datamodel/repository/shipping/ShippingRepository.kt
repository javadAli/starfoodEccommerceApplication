package com.example.starfood.datamodel.repository.shipping

import com.example.starfood.dao.CartFactorLocaleDataBase
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.applydiscount.ApplyDiscount
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Query

interface ShippingRepository{
    fun shippingData(psn:String, allMoney:Long,profit:Long):Single<ShippingDataModel>
    fun saveFactorsToLocale(item:CartFactorLocaleDataBase):Completable
    fun getLocaleShippingData():Single<ShippingDataModel>
    fun addShippingDataToLocale(item:ShippingDataModel):Completable
    fun checkTakhfifCode(psn:String, code:String):Single<ApplyDiscount>
    fun addFactor(psn:String,allMoney:Long,customerAddress:String,recivedTime:String,profit:Long): Single<AddFactorDataModel>
    fun getPaymentForm(psn:String,allMoney:Long):Single<String>
    fun successOnlinePay(psn:String, allMoney:String,tref:String,iN:String,iD:String,takhfif:String,takhfifCode:String,recivedTime:String,receviedAddress:String):Single<SuccessPayOnlineDataModel>
    fun successOnlineFactorPay(psn:String, allMoney:String,fatorSn:String,tref: String,iN:String,iD:String):Single<SuccessPayOnlineDataModel>
}