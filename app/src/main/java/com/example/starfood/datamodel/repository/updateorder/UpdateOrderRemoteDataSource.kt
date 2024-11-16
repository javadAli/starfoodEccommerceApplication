package com.example.starfood.datamodel.repository.updateorder

import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class UpdateOrderRemoteDataSource(val apiService: ApiService):UpdateOrderDataSource {
    override fun updateOrder(orderBYSSn: Long, amountUnit: Long, kalaId: Long): Single<String> = apiService.updateOrder(
        "Bearer "+TokenContainer.token!!,orderBYSSn,amountUnit,kalaId)
    override fun localePurchaseRegistration(order: CartDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun updateSubProduct(item: SubProductCategoryDataModel): Completable {
        TODO("Not yet implemented")
    }

    override fun updateExplainDataModel(item: ProductExplainDataModel): Completable {
        TODO("Not yet implemented")
    }


}