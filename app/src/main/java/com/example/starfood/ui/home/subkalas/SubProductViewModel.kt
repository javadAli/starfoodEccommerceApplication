package com.example.starfood.ui.home.subkalas

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodCompletableObserver
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductTable
import com.example.starfood.datamodel.detailcategorydatamodel.appendkala.AppendSubKalaDataModel
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailRepository
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SubProductViewModel(private val subProductRepository: SubProductRepository, private val updateRepository: UpdateOrderRepository, private val explainDetailProduct:ProductExplainDetailRepository):StarFoodViewModel(){
    val productDetailHeaderInfo = MutableLiveData<List<HeaderDetailCategoryModelItem>>()
    val productDetailInfo = MutableLiveData<SubProductCategoryDataModel>()
    val submitReminderLiveData = MutableLiveData<Int>()
    val cancelReminderLiveData = MutableLiveData<String>()
    val submitFavouriteLiveData = MutableLiveData<SubmitFavouriteDataModel>()
    val purchaseLiveData = MutableLiveData<String>()
    val appendProductLiveData = MutableLiveData<AppendSubKalaDataModel>()
    val localeDataLiveData = MutableLiveData<List<ProductLocaleSaved>>()
    var kalaList = ArrayList<SubProductTable>()
    fun getHeaderList(id:String){
        subProductRepository.getHeaderOfProductDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<List<HeaderDetailCategoryModelItem>>(compositeDisposable){
            override fun onSuccess(t: List<HeaderDetailCategoryModelItem>){
                productDetailHeaderInfo.value = t
                if (NetworkStateHolder.isConnected){
                    saveToLocalHeaderLists(t)
                }
            }
            override fun onError(e: Throwable) {
            }
        })
    }
    fun saveToLocalHeaderLists(t: List<HeaderDetailCategoryModelItem>) {
        subProductRepository.addHeaderProduct(t)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
            override fun onComplete() {
            }
        })
    }
    fun getSubProductList(id: String, psn: String, page: Int){
        if(!NetworkStateHolder.isConnected){
            subProductRepository.getProductDetailFromLocale(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally{progressLiveData.value = false}
                .subscribe(object : StarFoodSingleObserver<List<KalaSubGroup>>(compositeDisposable){
                    override fun onSuccess(t: List<KalaSubGroup>){
                        productDetailInfo.value=SubProductCategoryDataModel(
                            10,
                            "تومان",
                            mutableListOf(),
                            t,
                            "0",
                            "46",
                            id
                        )
                    }
                    override fun onError(e: Throwable) {
                    }
                })
        }else{
            subProductRepository.getProductDetail(id,psn,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<SubProductCategoryDataModel>(compositeDisposable){
                override fun onSuccess(t: SubProductCategoryDataModel){
                    productDetailInfo.value = t
                    saveSubProductToLocale(t)
                }
                override fun onError(e: Throwable) {
                }
            })
        }
    }
    fun saveSubProductToLocale(t: SubProductCategoryDataModel){
        for (i in t.listKala){
            val group = SubProductTable(t.currency,t.currencyName,i.Amount,i.BoughtAmount,i.GoodName,i.GoodSn,i.PackAmount,i.Price3,i.Price4
            ,i.SnGoodPriceSale,i.SnOrderBYS,i.UName,i.activePishKharid,i.activeTakhfifPercent,i.bought,i.callOnSale,i.favorite
            ,i.firstGroupId,i.freeExistance,i.overLine,i.requested,i.secondGroupId,i.secondUnit,i.SecondUnitAmount,t.logoPos,i.firstGroupId.toString())
            kalaList.add(group)
        }
        subProductRepository.addProductDetail(kalaList)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
            override fun onComplete(){
            }
        })
    }
    fun submitReminder(customerId:String, productId:String){
        subProductRepository.submitReminder(customerId,productId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<Int>(compositeDisposable){
            override fun onSuccess(t: Int) {
                submitReminderLiveData.value = t
            }
            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun cancelReminder(psn:String, gsn:String){
        subProductRepository.submitCancelReminder(psn,gsn)
       .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
            override fun onSuccess(t: String) {
                cancelReminderLiveData.value = t
            }
            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun submitFavourite(goodSn:String, psn:String){
        subProductRepository.submitFavourite(goodSn,psn)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<SubmitFavouriteDataModel>(compositeDisposable){
            override fun onSuccess(t: SubmitFavouriteDataModel) {
                submitFavouriteLiveData.value = t
            }
            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
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
    fun changeProductToSold(goodSn:Int,boughtAmount:Int,packAmount:Int){
        subProductRepository.changeProductToSold(goodSn,boughtAmount,packAmount)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
            override fun onComplete() {
            }
        })
    }
    fun updateSubProductTable(item: SubProductCategoryDataModel){
        updateRepository.updateSubProduct(item)
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
    fun updatePurchaseRegistration(orderBYS:Long,amountUnit:Long,kalaId:Long){
        updateRepository.updateOrder(orderBYS,amountUnit,kalaId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String){
                    purchaseLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun appendSubProduct(mainId:String,subId:String){
        if(NetworkStateHolder.isConnected) {
            subProductRepository.appendSubGroupKala(mainId, TokenContainer.psn!!, subId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                StarFoodSingleObserver<AppendSubKalaDataModel>(compositeDisposable) {
                override fun onSuccess(t: AppendSubKalaDataModel) {
                    appendProductLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }else{
            subProductRepository.getSubGroupProductDetailFromLocale(subId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                StarFoodSingleObserver<List<KalaSubGroup>>(compositeDisposable) {
                override fun onSuccess(t: List<KalaSubGroup>) {
                    appendProductLiveData.value =AppendSubKalaDataModel("10","تومان", mutableListOf(),t,"0",mainId,"44",subId)
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}