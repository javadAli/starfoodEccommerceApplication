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
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class SubProductRemoteDataSource(private val apiService: ApiService):SubProductDataSource{
    override fun submitReminder(customerId:String, productId:String):Single<Int> = apiService.submitReminder("Bearer "+TokenContainer.token!!,customerId,productId)
    override fun getHeaderOfProductDetail(id: String): Single<List<HeaderDetailCategoryModelItem>>  = apiService.getProductDetailHeaderList("Bearer "+TokenContainer.token!! ,id)
    override fun submitCancelReminder(psn:String, gsn:String):Single<String> = apiService.submitCancelReminder("Bearer "+TokenContainer.token!!,psn,gsn)
    override fun submitFavourite(goodSn:String, psn:String):Single<SubmitFavouriteDataModel> = apiService.submitFavourite("Bearer "+TokenContainer.token!!,goodSn,psn)
    override fun getAmountOfProduct(pCode: String, psn: String): Single<AmountProductDataModel> = apiService.getAmountProduct("Bearer "+TokenContainer.token!!,pCode,psn)
    override fun getFavourite(psn: String): Single<FavouriteDataModel> = apiService.getFavourite("Bearer "+TokenContainer.token!!,psn)
    override fun purchaseRegistration(psn: String, kalaId: String, amountUnit: String): Single<String> = apiService.purchaseRegistration("Bearer "+TokenContainer.token!!,psn,kalaId,amountUnit)
    override fun updateOrder(orderBYSSn: Long, amountUnit: Long, kalaId: Long): Single<String> =apiService.updateOrder("Bearer "+TokenContainer.token!!,orderBYSSn,amountUnit,kalaId);
    override fun getProductDetail(id: String, psn: String, page: Int): Single<SubProductCategoryDataModel>  = apiService.getProductDetailList("Bearer "+TokenContainer.token!!,id,psn,page)
    override fun getSubGroupProductDetailFromLocale(id: String): Single<List<KalaSubGroup>> {
        TODO("Not yet implemented")
    }
    override fun addProductDetail(item: List<SubProductTable>): Completable {
        TODO("Not yet implemented")
    }
    override fun getLocaleData(): Single<List<ProductLocaleSaved>> {
        TODO("Not yet implemented")
    }
    override fun deleteLocaleData(item: ProductLocaleSaved): Completable {
        TODO("Not yet implemented")
    }
    override fun getLocaleCartItem(goodSn: String): Single<ProductLocaleSaved> {
        TODO("Not yet implemented")
    }
    override fun getProductDetailFromLocale(id: String): Single<List<KalaSubGroup>> {
        TODO("Not yet implemented")
    }
    override fun localeSubmitReminder(productId: String): Completable {
        TODO("Not yet implemented")
    }
    override fun localeSubmitCancelReminder(gsn: String): Completable {
        TODO("Not yet implemented")
    }
    override fun localeSubmitFavourite(item: FavouriteDataModel): Completable {
        TODO("Not yet implemented")
    }
    override fun addHeaderProduct(item: List<HeaderDetailCategoryModelItem>): Completable {
        TODO("Not yet implemented")
    }
    override fun appendSubGroupKala(
        grId: String,
        psn: String,
        subKalaGroupId: String
    ): Single<AppendSubKalaDataModel> = apiService.appendSubGroupKala("Bearer "+TokenContainer.token!!,grId,psn,subKalaGroupId)
    override fun addLocaleChangesToTable(item: ProductLocaleSaved): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteLocaleOrder(goodSn: String): Completable {
        TODO("Not yet implemented")
    }

    override fun changeProductToSold(goodSn: Int, boughtAmount: Int, packAmount: Int): Completable {
        TODO("Not yet implemented")
    }

    override fun addGroupToLocale(item: List<CategoryDataModelItem>): Completable {
        TODO("Not yet implemented")
    }

    override fun localeGetFavourite(): Single<FavouriteDataModel> {
        TODO("Not yet implemented")
    }
    override fun addLocaleAmountOfProduct(item: AmountProductDataModel): Completable {
        TODO("Not yet implemented")
    }
    override fun localeGetAmountOfProduct(pCode: String): Single<AmountProductDataModel> {
        TODO("Not yet implemented")
    }
}