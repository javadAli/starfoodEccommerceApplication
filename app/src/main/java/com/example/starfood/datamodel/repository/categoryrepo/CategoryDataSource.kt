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
interface CategoryDataSource{
    fun addProductToTable(product:List<CategoryDataModelItem>): Completable
    fun getCategoryLocalList(): Single<List<CategoryDataModelItem>>
    fun getCategoryList():Single<List<CategoryDataModelItem>>
    fun searchList(psn:String,name:String):Single<List<SearchDataModelItem>>
    fun showAllKalaOfHomePart(psn:String,partId:String):Single<HomeAllKalaOfPartDataModel>
    fun getLocaleSliderToTable():Single<SliderDataModel>
    fun addSlider(item:SliderDataModel): Completable
    fun getSliders(psn:String): Single<SliderDataModel>
    fun getHomeParts(psn:String):Single<HomePartsDataModel>
    fun addHomePartsToLocale(item:HomePartsDataModel):Completable
    fun localeGetHomeParts():Single<HomePartsDataModel>
    fun addToBasketFromHomePage(psn:String,kalaId:String,amountUnit:String):Single<AddBasketHomeDataModel>
    fun UpdateBasketFromHomePage(orderBYSSn:String,kalaId:String,amountUnit:String):Single<UpdateBasketFromHomeDataModel>
    fun searchListOffline(name: String):Single<List<SearchDataModelItem>>
    fun sendAppTokenToServer(psn: String, token: String): Single<String>
    fun addAssesment(feedbackState: String, feedbackType: String): Single<String>
    fun checkLogin(): Single<String>
    fun logout():Single<String>
    fun checkButtonAllowance():Single<String>
}