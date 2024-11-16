package com.example.starfood.datamodel.repository.updateorder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.shipping.ShippingDataModel
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface UpdateOrderLocalDataSource:UpdateOrderDataSource {
    override fun updateOrder(orderBYSSn: Long, amountUnit: Long, kalaId: Long): Single<String>{
        TODO("Not yet implemented")
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun localePurchaseRegistration(order: CartDataModel): Completable

    override fun updateSubProduct(item: SubProductCategoryDataModel): Completable{
        TODO("Not yet implemented")
    }
    @Update
    override fun updateExplainDataModel(item: ProductExplainDataModel): Completable
}