package com.example.starfood.datamodel.repository.cartrepository

import androidx.room.Query
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single

interface CartDataSource {
    fun getCartItem(psn:String): Single<CartDataModel>
    fun addShippingDataToLocale(item: ShippingDataModel):Completable
    fun deleteFromLocaleCart(items: CartOrder):Completable
    fun saveCartItemToLocale(items:CartDataModel): Completable
    fun updateCartChanged(psn:String, snHDS:String): Single<CartDataModel>
    fun getLocaleCartItem():Single<List<CartOrder>>
    fun deleteFromCart(snOrderBYS:String): Single<String>
    fun getLocaleCartItemBasket(): Single<List<CartOrder>>
    fun updateLocalListOfOrder():Completable
    fun clearLocalListOfOrder():Completable
    fun deleteCartFromLocal(goodSn:String):Completable
}