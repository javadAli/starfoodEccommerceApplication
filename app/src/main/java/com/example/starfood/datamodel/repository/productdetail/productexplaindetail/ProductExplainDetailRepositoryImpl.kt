package com.example.starfood.datamodel.repository.productdetail.productexplaindetail

import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.Completable
import io.reactivex.Single

class ProductExplainDetailRepositoryImpl(private val productRemoteDataSource: ProductExplainDetailRemoteDataSource, private val productLocaleDataSource: ProductExplainDetailLocaleDataSource):ProductExplainDetailRepository {
    override fun getProductExplain(
        groupId: String,
        psn: String,
        id: String
    ): Single<ProductExplainDataModel> = productRemoteDataSource.getProductExplain(groupId,psn,id)
    override fun getProductExplainLocal(id:String): Single<ProductExplainDataModel> = productLocaleDataSource.getProductExplainLocal(id)
    override fun localeGetProductExplain(
       groupId: String,
        id: String
    ): Single<ProductExplainDataModel> =productLocaleDataSource.localeGetProductExplain(groupId,id)

    override fun addExplainToTable(item: ProductExplainDataModel): Completable = productLocaleDataSource.addExplainToTable(item)
    override fun updateExplainProduct(item: ProductExplainDataModel): Completable = productLocaleDataSource.updateExplainProduct(item)
}