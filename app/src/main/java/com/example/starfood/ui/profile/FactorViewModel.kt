package com.example.starfood.ui.profile

import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.profile.factorview.FactorViewDataModel
import com.example.starfood.datamodel.repository.profilerepo.ProfileRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FactorViewModel(private val profile:ProfileRepository):StarFoodViewModel(){

    val factorViewLiveData = MutableLiveData<FactorViewDataModel>()
    val paymentLiveData = MutableLiveData<String>()
    fun getFactors(factorSn:String){
        profile.getFactorView(TokenContainer.psn!!,factorSn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :StarFoodSingleObserver<FactorViewDataModel>(compositeDisposable){
                override fun onSuccess(t: FactorViewDataModel) {
                    factorViewLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
    }
    fun getFactorPayment(allMoney:String){
        profile.getFactorPaymentFormApi(allMoney,TokenContainer.psn!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String){
                    paymentLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
    }
}