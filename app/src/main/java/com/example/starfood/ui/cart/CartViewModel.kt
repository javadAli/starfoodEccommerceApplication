package com.example.starfood.ui.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodCompletableObserver
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.repository.cartrepository.CartRepository
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CartViewModel(private val cartRepository: CartRepository, private val updateRepository: UpdateOrderRepository,private val subProductRepository: SubProductRepository) : StarFoodViewModel() {
    val cartLiveData = MutableLiveData<CartDataModel>()
    val localeCartLiveData = MutableLiveData<ProductLocaleSaved>()
    val updateCartLiveData = MutableLiveData<CartDataModel>()
    val deleteLiveData = MutableLiveData<String>()
    val purchaseLiveData = MutableLiveData<String>()
    fun getCartList(psn:String){
        if(NetworkStateHolder.isConnected) {
            cartRepository.getCartItem(psn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<CartDataModel>(compositeDisposable) {
                override fun onSuccess(t: CartDataModel) {
                    cartLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }else{
            cartRepository.getLocaleCartItemBasket()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<List<CartOrder>>(compositeDisposable) {
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
                override fun onSuccess(t: List<CartOrder>) {
                    cartLiveData.value= CartDataModel(changedPriceState = "0", currency = 10, currencyName = "تومان", intervalBetweenBuys = "1000", logoPos = "0", minSalePriceFactor = "2000000", orderPishKarids =  mutableListOf(), orders = t)
                }
            })
        }
    }
    fun changeProductToSold(goodSn:Int,boughtAmount:Int,packAmount:Int){
        subProductRepository.changeProductToSold(goodSn,boughtAmount,packAmount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
                override fun onComplete() {
                }
            })
    }
    fun purchaseRegistration(kalaId:String,amount:String){
        subProductRepository.purchaseRegistration(TokenContainer.psn!!,kalaId,amount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String) {
                    purchaseLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun updateCart(snHDS:String){
        cartRepository.updateCartChanged(TokenContainer.psn!!,snHDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<CartDataModel>(compositeDisposable){
                override fun onSuccess(t: CartDataModel) {
                    try {
                        updateCartLiveData.value = t
                    }
                    catch (e:Exception){
                    }
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
    fun deleteFromList(snOrder:String){
        cartRepository.deleteFromCart(snOrder)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String) {
                    deleteLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
    fun addLocaleChangesToTable(item: ProductLocaleSaved){
        subProductRepository.addLocaleChangesToTable(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
                override fun onComplete() {
                }
            }
        )
    }
    fun updatePurchaseRegistration(orderBYS:Long,amountUnit:Long,kalaId:Long){
        updateRepository.updateOrder(orderBYS,amountUnit,kalaId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String) {
                    purchaseLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun clearLocalList(){
        cartRepository.clearLocalListOfOrder()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
            override fun onComplete() {
            }
            override fun onError(e: Throwable) {
            }
        })
        cartRepository.updateLocalListOfOrder()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
            override fun onComplete() {
            }
            override fun onError(e: Throwable) {
            }
        })
    }
    fun deleteCartFromLocal(goodSn: String){
        cartRepository.deleteCartFromLocal(goodSn)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
                override fun onComplete() {
                }
            })
    }
}