package com.example.starfood.ui.home.subkalas

import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AmountDialogViewModel(private val subProductRepository: SubProductRepository) : StarFoodViewModel(){
    var amountLiveData = MutableLiveData<AmountProductDataModel>()

    fun getAmount(pCode:String,psn:String){
        subProductRepository.getAmountOfProduct(pCode,psn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<AmountProductDataModel>(compositeDisposable) {
                override fun onSuccess(t: AmountProductDataModel) {
                    amountLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}