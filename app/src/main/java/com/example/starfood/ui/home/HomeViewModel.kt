package com.example.starfood.ui.home
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodCompletableObserver
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.categorydatamodel.HomeAllKalaOfPartDataModel
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.homparts.AddBasketHomeDataModel
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.homparts.UpdateBasketFromHomeDataModel
import com.example.starfood.datamodel.repository.cartrepository.CartRepository
import com.example.starfood.datamodel.repository.categoryrepo.CategoryRepository
import com.example.starfood.datamodel.repository.productdetail.SubProductRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.slider.SliderDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
class HomeViewModel(private val categoryRepository: CategoryRepository,private val subProductRepository: SubProductRepository,private val cartRepository: CartRepository
):StarFoodViewModel(){
    val sliderLiveData = MutableLiveData<SliderDataModel>()
    val homePartsLiveData = MutableLiveData<HomePartsDataModel>()
    val categoryLiveData = MutableLiveData<List<CategoryDataModelItem>>()
    val searchLiveData = MutableLiveData<List<SearchDataModelItem>>()
    var homeAllKalaOfPartLiveData= MutableLiveData<HomeAllKalaOfPartDataModel>()
    var submitFavouriteLiveData = MutableLiveData<SubmitFavouriteDataModel>()
    var purchaseLiveData=MutableLiveData<String>()
    var checklogin=MutableLiveData<String>()
    fun getSliders(){
        categoryRepository.getSliders(TokenContainer.psn!!)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<SliderDataModel>(compositeDisposable){
            override fun onSuccess(t: SliderDataModel){
                sliderLiveData.value = t
            }
            override fun onError(e: Throwable) {
            }
        })
    }
    fun getCategory(){
        categoryRepository.getCategoryList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<List<CategoryDataModelItem>>(compositeDisposable) {
            override fun onSuccess(t: List<CategoryDataModelItem>) {
                categoryLiveData.value = t
                saveCategoryToLocale(t)
            }
            override fun onError(e: Throwable) {
            }
        })
    }
    fun saveCategoryToLocale(items:List<CategoryDataModelItem>){
        var counterCategory =0
        categoryRepository.addProductToTable(items)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
            override fun onComplete(){
            }
        })
    }
    fun searchList(psn:String,goodName:String){
        if(NetworkStateHolder.isConnected) {
            categoryRepository.searchList(psn, goodName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object :
                StarFoodSingleObserver<List<SearchDataModelItem>>(compositeDisposable) {
                override fun onSuccess(t: List<SearchDataModelItem>) {
                    searchLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
        }else{
            categoryRepository.searchListOffline(goodName).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object :
                StarFoodSingleObserver<List<SearchDataModelItem>>(compositeDisposable) {
                override fun onSuccess(t: List<SearchDataModelItem>) {
                    searchLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
        }
    }
    fun showAllKalaOfHomePart(psn:String,partId:String){
        categoryRepository.showAllKalaOfHomePart(psn,partId).
        subscribeOn(Schedulers.newThread()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe(object : StarFoodSingleObserver<HomeAllKalaOfPartDataModel>(compositeDisposable){
            override fun onSuccess(t: HomeAllKalaOfPartDataModel) {
                homeAllKalaOfPartLiveData.value=t
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
    fun updatePurchaseRegistration(orderBYS:Long,amountUnit:Long,kalaId:Long){
        subProductRepository.updateOrder(orderBYS,amountUnit,kalaId)
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
    fun moveOrdersToOnline():String{
        cartRepository.getLocaleCartItem()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<List<CartOrder>>(compositeDisposable){
            override fun onSuccess(t: List<CartOrder>) {
                t.forEach{
                    subProductRepository.purchaseRegistration(TokenContainer.psn.toString(),it.GoodSn,it.Amount.toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                        override fun onError(e: Throwable) {
                        }
                        override fun onSuccess(t: String) {
                        }
                    })
                    subProductRepository.deleteLocaleOrder(it.GoodSn)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : StarFoodCompletableObserver(compositeDisposable){
                        override fun onComplete() {
                        }
                    })
                }
            }
            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return "Orders moved to online"
    }
    fun checkUserLogin(){
        categoryRepository.checkLogin()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<String>(compositeDisposable){
                override fun onSuccess(t: String) {
                        checklogin.value = t
                }
                override fun onError(e: Throwable) {
                    checklogin.value = e.message.toString()
                }
            })
    }
}
