package com.example.starfood.datamodel.repository.cartrepository

import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single

class CartRepositoryImpl(private val cartRemoteDataSource: CartRemoteDataSource, private val cartLocaleDataSource: CartLocaleDataSource):CartRepository {
    override fun getCartItem(psn: String): Single<CartDataModel> = cartRemoteDataSource.getCartItem(psn)
    override fun addShippingDataToLocale(item: ShippingDataModel): Completable = cartLocaleDataSource.addShippingDataToLocale(item)
    override fun getLocaleCartItem(): Single<List<CartOrder>> =cartLocaleDataSource.getLocaleCartItem()
    override fun getLocaleCartItemBasket(): Single<List<CartOrder>> =cartLocaleDataSource.getLocaleCartItemBasket()
    override fun clearLocalListOfOrder(): Completable = cartLocaleDataSource.clearLocalListOfOrder()
    override fun updateLocalListOfOrder():Completable=cartLocaleDataSource.updateLocalListOfOrder()
    override fun deleteCartFromLocal(goodSn: String): Completable =cartLocaleDataSource.deleteCartFromLocal(goodSn);
    override fun saveCartItemToLocale(items: CartDataModel): Completable = cartLocaleDataSource.saveCartItemToLocale(items)
    override fun deleteFromCart(snOrderBYS: String): Single<String> = cartRemoteDataSource.deleteFromCart(snOrderBYS)
    override fun deleteFromLocaleCart(items: CartOrder): Completable = cartLocaleDataSource.deleteFromLocaleCart(items)
    override fun updateCartChanged(psn: String, snHDS: String): Single<CartDataModel> = cartRemoteDataSource.updateCartChanged(psn,snHDS)
}