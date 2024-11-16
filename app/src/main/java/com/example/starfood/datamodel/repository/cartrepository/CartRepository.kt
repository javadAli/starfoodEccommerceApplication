package com.example.starfood.datamodel.repository.cartrepository

import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface CartRepository {
    fun getCartItem(psn:String):Single<CartDataModel>
    fun addShippingDataToLocale(item: ShippingDataModel):Completable
    fun getLocaleCartItem():Single<List<CartOrder>>
    fun saveCartItemToLocale(items:CartDataModel): Completable
    fun deleteFromCart(snOrderBYS:String):Single<String>
    fun deleteFromLocaleCart(items: CartOrder):Completable
    fun updateCartChanged(psn:String, snHDS:String): Single<CartDataModel>
    fun getLocaleCartItemBasket(): Single<List<CartOrder>>
    fun clearLocalListOfOrder():Completable
    fun updateLocalListOfOrder():Completable
    fun deleteCartFromLocal(goodSn:String):Completable
}