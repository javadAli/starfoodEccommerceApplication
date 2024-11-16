package com.example.starfood.ui.favourite

import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.favourite.FavouriteDataModel
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavouriteViewModel(private val subProductRepository: SubProductRepository):StarFoodViewModel() {
    val favouriteLiveData = MutableLiveData<FavouriteDataModel>()
    val submitReminderLiveData = MutableLiveData<Int>()
    val cancelReminderLiveData = MutableLiveData<String>()
    val submitFavouriteLiveData = MutableLiveData<SubmitFavouriteDataModel>()
    val purchaseLiveData = MutableLiveData<String>()
    fun getFavouriteList(psn:String){
        subProductRepository.getFavourite(psn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<FavouriteDataModel>(compositeDisposable){
                override fun onSuccess(t: FavouriteDataModel) {
                        favouriteLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun submitReminder(customerId:String, productId:String){
        subProductRepository.submitReminder(customerId,productId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<Int>(compositeDisposable){

                override fun onSuccess(t: Int) {
                    try {
                        submitReminderLiveData.value = t
                    }
                    catch (e:Exception){

                    }
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    fun cancelReminder(psn:String, gsn:String){

        subProductRepository.submitCancelReminder(psn,gsn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){

                override fun onSuccess(t: String) {
                    try {
                        cancelReminderLiveData.value = t
                    }
                    catch (e:Exception){

                    }
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    fun submitFavourite(goodSn:String, psn:String){
        subProductRepository.submitFavourite(goodSn,psn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<SubmitFavouriteDataModel>(compositeDisposable){

                override fun onSuccess(t: SubmitFavouriteDataModel) {
                    try {
                        submitFavouriteLiveData.value = t
                    }
                    catch (e:Exception){

                    }
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun purchaseRegistration(kalaId:String,amount:String){
        subProductRepository.purchaseRegistration(TokenContainer.psn!!,kalaId,amount)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){

                override fun onSuccess(t: String) {
                    try {
                        purchaseLiveData.value = t
                    }
                    catch (e:Exception){

                    }
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}