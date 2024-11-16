package com.example.starfood.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodCompletableObserver
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.common.asyncNetworkRequest
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductTable
import com.example.starfood.datamodel.repository.cartrepository.CartRepository
import com.example.starfood.datamodel.repository.categoryrepo.CategoryRepository
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel( val subProductRepository: SubProductRepository,  val categoryRepository: CategoryRepository
                    ,  val explainDetailProduct:ProductExplainDetailRepository, val cartRepository: CartRepository
): StarFoodViewModel(){
    val categoryResultLiveData = MutableLiveData<List<CategoryDataModelItem>>()
    val saveCategoryLiveData = MutableLiveData<String>()
    val productDetailInfo = MutableLiveData<SubProductCategoryDataModel>()
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    var kalaList = ArrayList<SubProductTable>()
    var subGroupList = ArrayList<HeaderDetailCategoryModelItem>()
    val tokenLiveData=MutableLiveData<String>()
    val assmentLiveData=MutableLiveData<String>()
    val buttonAllowance=MutableLiveData<String>()
    fun getCategory(){
        categoryRepository.getCategoryList()
            .asyncNetworkRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<List<CategoryDataModelItem>>(compositeDisposable){
                override fun onSuccess(t: List<CategoryDataModelItem>){
                    categoryResultLiveData.value = t
                    saveCategoryToLocale(t)
                }
                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun checkButtonAllowance(){
        categoryRepository.checkButtonAllowance()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onError(e: Throwable) {
                }
                override fun onSuccess(t: String) {
                    buttonAllowance.value = t.toString()
                }
            })
    }
    fun saveCategoryToLocale(items:List<CategoryDataModelItem>){
        coroutineScope.launch {
            categoryRepository.addProductToTable(items)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
                    override fun onComplete(){
                        saveCategoryLiveData.value = "لیست گروه های اصلی ذخیره شده"
                    }
                })
        }
    }
    fun getSubProductList(id:String, psn:String){
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
                        TODO("Not yet implemented")
                    }
                })
        }else{
            subProductRepository.getProductDetail(id, psn, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<SubProductCategoryDataModel>(compositeDisposable){
                override fun onSuccess(t: SubProductCategoryDataModel){
                    productDetailInfo.value = t
                    saveSubProductToLocale(t,id)
                }
                override fun onError(e: Throwable) {
                }
            })
        }
    }
    fun saveSubProductToLocale(t: SubProductCategoryDataModel, id: String){
        kalaList?.clear()
        subGroupList?.clear()
        for (i in t.listGroups){
            subGroupList.add(i)
        }
        if(subGroupList.size>0){
            saveToLocalHeaderLists(subGroupList)
        }
        for (i in t.listKala){
            val product = SubProductTable(t.currency,t.currencyName,i.Amount,i.BoughtAmount,i.GoodName,i.GoodSn,i.PackAmount,i.Price3,i.Price4
                ,i.SnGoodPriceSale,i.SnOrderBYS,i.UName,i.activePishKharid,i.activeTakhfifPercent,i.bought,i.callOnSale,i.favorite
                ,i.firstGroupId,i.freeExistance,i.overLine,i.requested,i.secondGroupId,i.secondUnit,i.SecondUnitAmount,t.logoPos,i.firstGroupId.toString())
            kalaList.add(product)
        }
        if(kalaList.size>0) {
            subProductRepository.addProductDetail(kalaList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodCompletableObserver(compositeDisposable) {
                override fun onComplete() {
                }
                override fun onError(e: Throwable) {
                }
            })
        }
    }

    fun sendAppTokenToServer(token: String?){
        categoryRepository.sendAppTokenToServer(TokenContainer.psn!!,token!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){

                override fun onError(e: Throwable) {
                }

                override fun onSuccess(t: String) {
                    tokenLiveData.value = t
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

    fun addAssesment(feedBackSate:String,feedBackType:String){
        categoryRepository.addAssesment(feedBackSate, feedBackType)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
            override fun onSuccess(t: String){
                assmentLiveData.value = t
            }
            override fun onError(e: Throwable) {
            }
        })
    }

    fun logout(){
        categoryRepository.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String){
                    assmentLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
    }

}