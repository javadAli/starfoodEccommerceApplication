package com.example.starfood.ui.home.subkalas
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodCompletableObserver
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailRepository
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
class ProductExplainViewModel(private val productExplainDetailRepository: ProductExplainDetailRepository, private val subProductRepository: SubProductRepository, private val updateRepository: UpdateOrderRepository):
    StarFoodViewModel(){
    val productExplainInfo = MutableLiveData<ProductExplainDataModel>()
    val purchaseLiveData = MutableLiveData<String>()
    fun getExplainProduct(groupId:String,psn:String,id:String){
        progressLiveData.value = true
        productExplainDetailRepository.getProductExplain(groupId,psn,id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .doFinally{progressLiveData.value = false}
        .subscribe(object :StarFoodSingleObserver<ProductExplainDataModel>(compositeDisposable){
            override fun onSuccess(t:ProductExplainDataModel){
                productExplainInfo.value=t
            }
            override fun onError(e: Throwable) {
            }
        })
    }
    fun getExplainProductLocal(id:String) {
        productExplainDetailRepository.getProductExplainLocal(id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object :StarFoodSingleObserver<ProductExplainDataModel>(compositeDisposable){
            override fun onSuccess(t: ProductExplainDataModel) {
                productExplainInfo.value = t
                //saveExplain(t)
            }
            override fun onError(e: Throwable) {
            }
        })
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
    fun addLocaleChangesToTable(item: ProductLocaleSaved){
        subProductRepository.addLocaleChangesToTable(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
                override fun onComplete() {
                }

            })
    }
    fun updatePurchaseRegistration(orderBYS:Long,amountUnit:Long,kalaId:Long){
        updateRepository.updateOrder(orderBYS,amountUnit,kalaId)
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

    fun submitFavourite(goodSn:String, psn:String){
        subProductRepository.submitFavourite(goodSn,psn)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<SubmitFavouriteDataModel>(compositeDisposable){
                override fun onSuccess(t: SubmitFavouriteDataModel) {
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