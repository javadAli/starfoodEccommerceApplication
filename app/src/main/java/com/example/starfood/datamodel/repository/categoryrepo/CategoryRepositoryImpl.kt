package com.example.starfood.datamodel.repository.categoryrepo

import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.categorydatamodel.HomeAllKalaOfPartDataModel
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.homparts.AddBasketHomeDataModel
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.homparts.UpdateBasketFromHomeDataModel
import com.example.starfood.datamodel.slider.SliderDataModel
import io.reactivex.Completable
import io.reactivex.Single
class CategoryRepositoryImpl(private val remote: CategoryRemoteDataSource, private val locale: CategoryLocaleDataSource)
    :CategoryRepository {
    override fun getCategoryList(): Single<List<CategoryDataModelItem>> = if (NetworkStateHolder.isConnected) remote.getCategoryList() else locale.getCategoryLocalList()
    // Process the categoryList as needed
    override fun addProductToTable(product: List<CategoryDataModelItem>): Completable = locale.addProductToTable(product)

    //override fun addOrderToLocal(product: List<OrderLocalTable>) :Completable =locale.addOrderToLocal(product)
    override fun searchList(psn: String, name: String): Single<List<SearchDataModelItem>> =remote.searchList(psn,name)
    override fun searchListOffline( name: String): Single<List<SearchDataModelItem>> =locale.searchListOffline(name)
    override fun showAllKalaOfHomePart(psn: String,partId: String): Single<HomeAllKalaOfPartDataModel> = remote.showAllKalaOfHomePart(psn,partId)
    override fun sendAppTokenToServer(psn: String, token: String):Single<String> = remote.sendAppTokenToServer(psn,token)
    override fun addAssesment(feedbackState: String, feedbackType: String): Single<String> = remote.addAssesment(feedbackState,feedbackType)
    override fun checkLogin(): Single<String> = remote.checkLogin()
    override fun logout(): Single<String> = remote.logout()
    override fun checkButtonAllowance(): Single<String> = remote.checkButtonAllowance()

    override fun getSliders(psn: String): Single<SliderDataModel> = if (NetworkStateHolder.isConnected) remote.getSliders(psn) else locale.getLocaleSliderToTable()
    override fun addSlider(item: SliderDataModel): Completable =locale.addSlider(item)
    override fun getLocaleSliderToTable(): Single<SliderDataModel> =locale.getLocaleSliderToTable()
    override fun getHomeParts(psn: String): Single<HomePartsDataModel> = if (NetworkStateHolder.isConnected) remote.getHomeParts(psn) else locale.localeGetHomeParts()
    override fun localeGetHomeParts(): Single<HomePartsDataModel> = locale.localeGetHomeParts()
    override fun addHomePartsToLocale(item: HomePartsDataModel): Completable = locale.addHomePartsToLocale(item)
    override fun addToBasketFromHomePage(
        psn: String,
        kalaId: String,
        amountUnit: String
    ): Single<AddBasketHomeDataModel> = remote.addToBasketFromHomePage(psn,kalaId,amountUnit)
    override fun UpdateBasketFromHomePage(
        orderBYSSn: String,
        kalaId: String,
        amountUnit: String
    ): Single<UpdateBasketFromHomeDataModel> = remote.UpdateBasketFromHomePage(orderBYSSn,kalaId,amountUnit)
}