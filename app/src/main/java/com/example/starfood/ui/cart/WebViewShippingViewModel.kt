package com.example.starfood.ui.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.repository.shipping.ShippingRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WebViewShippingViewModel(private  val repository:ShippingRepository):StarFoodViewModel(){
     var successResultLiveData = MutableLiveData<SuccessPayOnlineDataModel>()
    fun successPaymentResult(allMoney:String,tref:String,iN:String,iD:String,takhfif:String,takhfifCode:String,recivedTime:String,receviedAddress:String){
        repository.successOnlinePay(TokenContainer.psn!!,allMoney,tref,iN,iD,takhfif,takhfifCode,recivedTime,receviedAddress)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<SuccessPayOnlineDataModel>(compositeDisposable){
            override fun onSuccess(t: SuccessPayOnlineDataModel) {
                successResultLiveData.value = t
            }
            override fun onError(e: Throwable) {
            }
        })
    }

    fun successFactorPaymentResult(allMoney:String,factorSn:String,tref:String,iN:String,iD:String){
        repository.successOnlineFactorPay(TokenContainer.psn!!,allMoney,factorSn,tref,iN,iD)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<SuccessPayOnlineDataModel>(compositeDisposable){
                override fun onSuccess(t: SuccessPayOnlineDataModel) {
                    successResultLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
    }
}