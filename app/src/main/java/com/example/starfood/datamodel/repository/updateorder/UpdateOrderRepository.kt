package com.example.starfood.datamodel.repository.updateorder

import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface UpdateOrderRepository {
    fun updateOrder(orderBYSSn:Long, amountUnit:Long,kalaId:Long):Single<String>
    fun localePurchaseRegistration(order: CartDataModel): Completable
    fun updateSubProduct(item: SubProductCategoryDataModel):Completable
    fun updateExplainDataModel(item:ProductExplainDataModel):Completable

}