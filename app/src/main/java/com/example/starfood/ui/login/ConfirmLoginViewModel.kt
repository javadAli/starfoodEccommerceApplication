package com.example.starfood.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import com.example.starfood.datamodel.repository.confirmlogin.ConfirmLoginRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ConfirmLoginViewModel(private val confirmLoginRepository: ConfirmLoginRepository):StarFoodViewModel() {
    val confirmLiveData = MutableLiveData<ConfirmLoginDataModel>()
    fun getConfirm(customerId:String, token:String) {
        confirmLoginRepository.getConfirm(customerId,token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<ConfirmLoginDataModel>(compositeDisposable) {
                override fun onSuccess(t: ConfirmLoginDataModel) {
                    confirmLiveData.value = t
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}