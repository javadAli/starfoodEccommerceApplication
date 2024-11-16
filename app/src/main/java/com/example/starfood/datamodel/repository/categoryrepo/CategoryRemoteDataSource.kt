package com.example.starfood.datamodel.repository.categoryrepo

import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.categorydatamodel.HomeAllKalaOfPartDataModel
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.homparts.AddBasketHomeDataModel
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.homparts.UpdateBasketFromHomeDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.slider.SliderDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class CategoryRemoteDataSource(private val apiService: ApiService): CategoryDataSource {
    override fun addProductToTable(product: List<CategoryDataModelItem>): Completable {
        TODO("Not yet implemented")
    }

    override fun getCategoryLocalList(): Single<List<CategoryDataModelItem>> {
        TODO("Not yet implemented")
    }

    override fun localeGetHomeParts(): Single<HomePartsDataModel> {
        TODO("Not yet implemented")
    }

    override fun getCategoryList(): Single<List<CategoryDataModelItem>> =
        apiService.getCategoryList("Bearer "+TokenContainer.token!!)

    override fun searchList(psn: String, name: String): Single<List<SearchDataModelItem>> =
        apiService.searchList("Bearer "+TokenContainer.token!!,psn, name)

    override fun getLocaleSliderToTable(): Single<SliderDataModel> {
        TODO("Not yet implemented")
    }

    override fun addSlider(item: SliderDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun getSliders(psn: String): Single<SliderDataModel> = apiService.getSliders("Bearer "+TokenContainer.token!!,psn)
    override fun getHomeParts(psn: String): Single<HomePartsDataModel> =
        apiService.getHomeParts("Bearer "+TokenContainer.token!!,psn)

    override fun addHomePartsToLocale(item: HomePartsDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun addToBasketFromHomePage(
        psn: String,
        kalaId: String,
        amountUnit: String
    ): Single<AddBasketHomeDataModel> = apiService.addToBasketFromHomePage("Bearer "+TokenContainer.token!!,psn, kalaId, amountUnit)

    override fun UpdateBasketFromHomePage(
        orderBYSSn: String,
        kalaId: String,
        amountUnit: String
    ): Single<UpdateBasketFromHomeDataModel> =
        apiService.updateBasketItemFromHomePage("Bearer "+TokenContainer.token!!,orderBYSSn, kalaId, amountUnit)

    override fun searchListOffline(name: String): Single<List<SearchDataModelItem>> {
        TODO("Not yet implemented")
    }

    override fun sendAppTokenToServer(psn: String, appToken: String): Single<String> =apiService.sendAppTokenToServer("Bearer "+TokenContainer.token!!,psn,appToken);
    override fun addAssesment(feedbackState: String, feedbackType: String): Single<String> =  apiService.addCustomerFeedback("Bearer "+TokenContainer.token!!,TokenContainer.psn!!,feedbackState,feedbackType)
    override fun showAllKalaOfHomePart(psn: String, partId: String): Single<HomeAllKalaOfPartDataModel> = apiService.showAllKalaOfHomePart("Bearer "+TokenContainer.token!!,psn,partId)
    override fun checkLogin(): Single<String> = apiService.checkLogin("Bearer "+TokenContainer.token!!,TokenContainer.psn!!)
    override fun logout():Single<String> = apiService.logout("Bearer "+TokenContainer.token!!,TokenContainer.token!!,TokenContainer.psn!!)
    override fun checkButtonAllowance(): Single<String> =apiService.checkButtonAllowance("Bearer "+TokenContainer.token!!,TokenContainer.psn!!)
}