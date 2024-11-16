package com.example.starfood.datamodel.repository.cartrepository

import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class CartRemoteDataSource(private val apiService: ApiService):CartDataSource{
    override fun getCartItem(psn: String): Single<CartDataModel> = apiService.getCartItem("Bearer "+TokenContainer.token!!,psn)
    override fun addShippingDataToLocale(item: ShippingDataModel): Completable {
        TODO("Not yet implemented")
    }
    override fun deleteFromLocaleCart(items: CartOrder): Completable {
        TODO("Not yet implemented")
    }
    override fun saveCartItemToLocale(items: CartDataModel): Completable { TODO("Not yet implemented") }
    override fun updateCartChanged(psn: String, snHDS: String): Single<CartDataModel> = apiService.updateCartChanged("Bearer "+TokenContainer.token!!,psn,snHDS)
    override fun getLocaleCartItem(): Single<List<CartOrder>> {
        TODO("Not yet implemented")
    }
    override fun deleteFromCart(snOrderBYS: String): Single<String> = apiService.deleteFromCart(
        "Bearer "+TokenContainer.token!!,snOrderBYS)
    override fun getLocaleCartItemBasket(): Single<List<CartOrder>> {
        TODO("Not yet implemented")
    }
    override fun updateLocalListOfOrder(): Completable {
        TODO("Not yet implemented")
    }
    override fun clearLocalListOfOrder(): Completable {
        TODO("Not yet implemented")
    }
    override fun deleteCartFromLocal(goodSn: String): Completable {
        TODO("Not yet implemented")
    }
}