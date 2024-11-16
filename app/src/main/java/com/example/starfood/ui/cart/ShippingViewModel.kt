package com.example.starfood.ui.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodCompletableObserver
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.dao.CartFactorLocaleDataBase
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.applydiscount.ApplyDiscount
import com.example.starfood.datamodel.repository.shipping.ShippingRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShippingViewModel(private val shippingRepository: ShippingRepository):StarFoodViewModel() {
    var shippingLiveData = MutableLiveData<ShippingDataModel>()
    var addFactorLiveData = MutableLiveData<AddFactorDataModel>()
    var checkTakhfifLiveData = MutableLiveData<ApplyDiscount>()
    var paymentFormLiveData = MutableLiveData<String>()

    fun shippingData(psn:String,allMoney:Long,profit:Long){
        shippingRepository.shippingData(psn,allMoney,profit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<ShippingDataModel>(compositeDisposable) {
                override fun onSuccess(t: ShippingDataModel) {
                    shippingLiveData.value = t
                    //addToLocaleShippingData(t)
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun addFactor(psn:String,allMoney:Long,customerAddress:String,receivedTime:String,profit:Long){
        shippingRepository.addFactor(psn,allMoney,customerAddress,receivedTime,profit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<AddFactorDataModel>(compositeDisposable) {
                override fun onSuccess(t: AddFactorDataModel){
                    addFactorLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun checkTakhfif(psn:String,code:String){
        shippingRepository.checkTakhfifCode(psn,code)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<ApplyDiscount>(compositeDisposable) {
                override fun onSuccess(t: ApplyDiscount){
                    checkTakhfifLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun getPaymentForm(psn:String,allMoney:Long){
        shippingRepository.getPaymentForm(psn,allMoney)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable) {
                override fun onSuccess(t: String){
                    paymentFormLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
    }
}