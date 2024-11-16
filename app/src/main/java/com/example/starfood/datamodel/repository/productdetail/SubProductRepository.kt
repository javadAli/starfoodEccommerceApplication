package com.example.starfood.datamodel.repository.productdetail

import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductTable
import com.example.starfood.datamodel.detailcategorydatamodel.appendkala.AppendSubKalaDataModel
import com.example.starfood.datamodel.favourite.FavouriteDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface SubProductRepository{
    fun getProductDetail(id: String, psn: String, page: Int):Single<SubProductCategoryDataModel>
    fun getLocaleCartItem(goodSn:String):Single<ProductLocaleSaved>
    fun getLocaleData():Single<List<ProductLocaleSaved>>
    fun deleteLocaleData(item:ProductLocaleSaved):Completable
    fun addProductDetail(item:List<SubProductTable>):Completable
    fun getProductDetailFromLocale(id:String):Single<List<KalaSubGroup>>
    fun getSubGroupProductDetailFromLocale(subGroupId:String):Single<List<KalaSubGroup>>
    fun submitReminder(customerId:String,productId:String):Single<Int>
    fun localeSubmitReminder(productId:String):Completable
    fun localeSubmitCancelReminder(gsn:String):Completable
    fun localeSubmitFavourite(item:FavouriteDataModel):Completable
    fun localeGetFavourite():Single<FavouriteDataModel>
    fun addLocaleAmountOfProduct(item:AmountProductDataModel):Completable
    fun localeGetAmountOfProduct(pCode:String):Single<AmountProductDataModel>
    fun submitCancelReminder(psn:String,gsn:String):Single<String>
    fun submitFavourite(goodSn:String,psn:String):Single<SubmitFavouriteDataModel>
    fun getAmountOfProduct(pCode:String,psn:String):Single<AmountProductDataModel>
    fun purchaseRegistration(psn:String,kalaId:String,amountUnit:String):Single<String>
    fun updateOrder(orderBYSSn:Long, amountUnit:Long,kalaId:Long):Single<String>
    fun getFavourite(psn:String):Single<FavouriteDataModel>
    fun getHeaderOfProductDetail(id:String):Single<List<HeaderDetailCategoryModelItem>>
    fun addHeaderProduct(item:List<HeaderDetailCategoryModelItem>):Completable
    fun appendSubGroupKala(grId: String,psn: String,subKalaGroupId:String): Single<AppendSubKalaDataModel>
    fun addLocaleChangesToTable(item: ProductLocaleSaved):Completable
    fun changeProductToSold(goodSn: Int,boughtAmount:Int,packAmount:Int):Completable
    fun deleteLocaleOrder(goodSn: String):Completable
    fun addGroupToLocale(item:List<CategoryDataModelItem>):Completable
}