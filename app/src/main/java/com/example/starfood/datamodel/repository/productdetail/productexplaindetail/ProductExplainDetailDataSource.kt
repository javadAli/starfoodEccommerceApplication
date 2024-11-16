package com.example.starfood.datamodel.repository.productdetail.productexplaindetail

import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface ProductExplainDetailDataSource {
    fun getProductExplain(groupId:String,psn:String,id:String): Single<ProductExplainDataModel>
    fun localeGetProductExplain(groupId:String,id:String): Single<ProductExplainDataModel>
    fun addExplainToTable(item: ProductExplainDataModel): Completable
    fun updateExplainProduct(item: ProductExplainDataModel):Completable
    fun getProductExplainLocal(id: String): Single<ProductExplainDataModel>
}