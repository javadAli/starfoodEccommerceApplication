package com.example.starfood.datamodel.repository.categoryrepo

import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.categorydatamodel.HomeAllKalaOfPartDataModel
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.homparts.AddBasketHomeDataModel
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.homparts.UpdateBasketFromHomeDataModel
import com.example.starfood.datamodel.slider.SliderDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface CategoryRepository {
    fun getCategoryList():Single<List<CategoryDataModelItem>>
    fun addProductToTable(product:List<CategoryDataModelItem>):Completable
    fun searchList(psn:String,name:String):Single<List<SearchDataModelItem>>
    fun searchListOffline(name:String):Single<List<SearchDataModelItem>>
    fun getSliders(psn:String): Single<SliderDataModel>
    fun addSlider(item:SliderDataModel): Completable
    fun getLocaleSliderToTable():Single<SliderDataModel>
    fun getHomeParts(psn:String):Single<HomePartsDataModel>
    fun localeGetHomeParts():Single<HomePartsDataModel>
    fun addHomePartsToLocale(item:HomePartsDataModel):Completable
    fun addToBasketFromHomePage(psn:String,kalaId:String,amountUnit:String):Single<AddBasketHomeDataModel>
    fun UpdateBasketFromHomePage(orderBYSSn:String,kalaId:String,amountUnit:String):Single<UpdateBasketFromHomeDataModel>
    fun showAllKalaOfHomePart(psn:String,partId:String):Single<HomeAllKalaOfPartDataModel>
    fun sendAppTokenToServer(psn: String, token: String): Single<String>
    fun addAssesment(feedbackState: String, feedbackType: String): Single<String>
    fun checkLogin(): Single<String>
    fun logout(): Single<String>
    fun checkButtonAllowance():Single<String>
}