package com.example.starfood.datamodel.repository.productdetail.productexplaindetail

import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class ProductExplainDetailRemoteDataSource(private val apiService: ApiService):ProductExplainDetailDataSource{
    override fun getProductExplain(
        groupId: String,
        psn: String,
        id: String
    ): Single<ProductExplainDataModel> = apiService.getProductExplain("Bearer "+TokenContainer.token!!,groupId,psn,id)

    override fun localeGetProductExplain(
        groupId: String,
        id: String
    ): Single<ProductExplainDataModel> {
        TODO("Not yet implemented")
    }

    override fun addExplainToTable(item: ProductExplainDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun updateExplainProduct(item: ProductExplainDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun getProductExplainLocal(
        id: String
    ): Single<ProductExplainDataModel> {
        TODO("Not yet implemented")
    }
}