package com.example.starfood.datamodel.repository.updateorder

import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single

class UpdateOrderRepoImpl(private val remoteDataSource: UpdateOrderRemoteDataSource, private val localDataSource: UpdateOrderLocalDataSource) : UpdateOrderRepository{
    override fun updateOrder(orderBYSSn: Long, amountUnit: Long, kalaId: Long): Single<String> = remoteDataSource.updateOrder(orderBYSSn,amountUnit,kalaId)
    override fun localePurchaseRegistration(order: CartDataModel): Completable = localDataSource.localePurchaseRegistration(order)

    override fun updateSubProduct(item: SubProductCategoryDataModel): Completable = localDataSource.updateSubProduct(item)

    override fun updateExplainDataModel(item: ProductExplainDataModel): Completable = localDataSource.updateExplainDataModel(item)

}