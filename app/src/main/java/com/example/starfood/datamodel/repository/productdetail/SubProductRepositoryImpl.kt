package com.example.starfood.datamodel.repository.productdetail

import com.example.starfood.common.networkMonitoring.NetworkStateHolder
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

class SubProductRepositoryImpl(private val remote: SubProductRemoteDataSource, private val locale: SubProductLocaleDataSource):SubProductRepository {
    override fun getProductDetail(id: String, psn: String, page: Int): Single<SubProductCategoryDataModel> = remote.getProductDetail(id,psn,page)
    override fun getLocaleCartItem(goodSn: String): Single<ProductLocaleSaved> = locale.getLocaleCartItem(goodSn)
    override fun getLocaleData(): Single<List<ProductLocaleSaved>> = locale.getLocaleData()
    override fun deleteLocaleData(item: ProductLocaleSaved): Completable = locale.deleteLocaleData(item)
    override fun addProductDetail(item: List<SubProductTable>): Completable = locale.addProductDetail(item)
    override fun getProductDetailFromLocale(id: String): Single<List<KalaSubGroup>> =locale.getProductDetailFromLocale(id)
    override fun getSubGroupProductDetailFromLocale(id: String): Single<List<KalaSubGroup>> = locale.getSubGroupProductDetailFromLocale(id)
    override fun submitReminder(customerId: String, productId: String): Single<Int> = remote.submitReminder(customerId,productId)
    override fun submitCancelReminder(psn: String, gsn: String): Single<String> = remote.submitCancelReminder(psn,gsn)
    override fun submitFavourite(goodSn: String, psn: String): Single<SubmitFavouriteDataModel> = remote.submitFavourite(goodSn,psn)
    override fun getAmountOfProduct(pCode: String, psn: String): Single<AmountProductDataModel> = if(NetworkStateHolder.isConnected) remote.getAmountOfProduct(pCode,psn)
    else locale.localeGetAmountOfProduct(pCode)
    override fun purchaseRegistration(psn: String, kalaId: String, amountUnit: String): Single<String> = remote.purchaseRegistration(psn,kalaId,amountUnit)
    override fun updateOrder(orderBYSSn: Long, amountUnit: Long, kalaId: Long): Single<String> =remote.updateOrder(orderBYSSn,amountUnit,kalaId);
    override fun getFavourite(psn: String): Single<FavouriteDataModel> = remote.getFavourite(psn)
    override fun getHeaderOfProductDetail(id: String): Single<List<HeaderDetailCategoryModelItem>> = if (NetworkStateHolder.isConnected) remote.getHeaderOfProductDetail(id)
    else locale.getHeaderOfProductDetail(id)
    override fun addHeaderProduct(item: List<HeaderDetailCategoryModelItem>): Completable = locale.addHeaderProduct(item)
    override fun appendSubGroupKala(grId: String, psn: String, subKalaGroupId: String): Single<AppendSubKalaDataModel> = remote.appendSubGroupKala(grId,psn,subKalaGroupId)
    override fun addLocaleChangesToTable(item: ProductLocaleSaved): Completable=locale.addLocaleChangesToTable(item)
    override fun changeProductToSold(goodSn: Int,boughtAmount:Int,packAmount:Int): Completable =locale.changeProductToSold(goodSn,boughtAmount,packAmount);
    override fun deleteLocaleOrder(goodSn: String): Completable = locale.deleteLocaleOrder(goodSn)
    override fun localeSubmitReminder(productId: String): Completable = locale.localeSubmitReminder(productId)
    override fun localeSubmitCancelReminder(gsn: String): Completable = locale.localeSubmitCancelReminder(gsn)
    override fun localeSubmitFavourite(item: FavouriteDataModel): Completable = locale.localeSubmitFavourite(item)
    override fun localeGetFavourite(): Single<FavouriteDataModel> = locale.localeGetFavourite()
    override fun addLocaleAmountOfProduct(item: AmountProductDataModel): Completable = locale.addLocaleAmountOfProduct(item)
    override fun localeGetAmountOfProduct(pCode: String): Single<AmountProductDataModel> = locale.localeGetAmountOfProduct(pCode)
    override fun addGroupToLocale(item: List<CategoryDataModelItem>): Completable = locale.addGroupToLocale(item)
}