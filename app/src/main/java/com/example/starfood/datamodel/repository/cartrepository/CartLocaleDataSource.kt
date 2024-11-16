package com.example.starfood.datamodel.repository.cartrepository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface CartLocaleDataSource:CartDataSource {
    override fun getCartItem(psn: String): Single<CartDataModel>{
        TODO("Not yet implemented")
    }
    @Delete
    override fun deleteFromLocaleCart(items: CartOrder): Completable
    @Query("SELECT productLocaleSaved_tbl.amountBuy as Amount,Amount as AmountExist,5 as CompanyNo,CURRENT_TIMESTAMP as DateOrder,Price3 as Fi,GoodName,productLocaleSaved_tbl.goodSn as GoodSn,cast(productLocaleSaved_tbl.amountBuy/SecondUnitAmount as int)PackAmount,Price4 as Price,0 as Price1,Price3,Price4,SecondUnitAmount,productLocaleSaved_tbl.goodSn as SnGood,0 SnHDS,0 SnOrderBYS,UName,0 changedPrice,0 freeExistance,Amount maxSale,secondUnit AS secondUnitName from productLocaleSaved_tbl inner join subProduct_tbl on productLocaleSaved_tbl.goodSn=subProduct_tbl.GoodSn")
    override fun getLocaleCartItem(): Single<List<CartOrder>>
    @Query("SELECT BoughtAmount as Amount,Amount as AmountExist,5 as CompanyNo,CURRENT_TIMESTAMP as DateOrder,Price3 as Fi,GoodName, GoodSn,PackAmount,Price4 as Price,0 as Price1,Price3,Price4,SecondUnitAmount,GoodSn SnGood,0 SnHDS,0 SnOrderBYS,UName,0 changedPrice,0 freeExistance,Amount maxSale,secondUnit AS secondUnitName from subProduct_tbl where bought='Yes'")
    override fun getLocaleCartItemBasket(): Single<List<CartOrder>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addShippingDataToLocale(item: ShippingDataModel): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun saveCartItemToLocale(items: CartDataModel): Completable
    override fun updateCartChanged(psn: String, snHDS: String): Single<CartDataModel>{
        TODO("Not yet implemented")
    }
    override fun deleteFromCart(snOrderBYS: String): Single<String> {
        TODO("Not yet implemented")
    }
    @Query("update subProduct_tbl set bought='No',BoughtAmount=0")
    override fun updateLocalListOfOrder():Completable
    @Query("DELETE FROM productLocaleSaved_tbl")
    override fun clearLocalListOfOrder():Completable
    @Query("update subProduct_tbl set bought='No',BoughtAmount=0 where GoodSn=:goodSn")
    override fun deleteCartFromLocal(goodSn:String):Completable
}