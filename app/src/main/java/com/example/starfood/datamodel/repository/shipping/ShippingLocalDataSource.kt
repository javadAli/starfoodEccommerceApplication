package com.example.starfood.datamodel.repository.shipping

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starfood.dao.CartFactorLocaleDataBase
import com.example.starfood.datamodel.addfactor.AddFactorDataModel
import com.example.starfood.datamodel.applydiscount.ApplyDiscount
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.datamodel.shipping.SuccessPayOnlineDataModel
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface ShippingLocalDataSource:ShippingDataSource {

    override fun shippingData(psn: String, allMoney: Long, profit: Long): Single<ShippingDataModel>{
        TODO("Not yet implemented")
    }
    @Query("SELECT * FROM shipping_tbl")
    override fun getLocaleShippingData(): Single<ShippingDataModel>
    override fun addFactor(
        psn: String,
        allMoney: Long,
        customerAddress: String,
        recivedTime: String,profit:Long
    ): Single<AddFactorDataModel> {
        TODO("Not yet implemented")
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun saveFactorsToLocale(item: CartFactorLocaleDataBase): Completable
    override fun checkTakhfifCode(psn: String, code: String): Single<ApplyDiscount> {
        TODO("Not yet implemented")
    }

    override fun getPaymentForm(psn: String, allMoney: Long): Single<String> {
        TODO("Not yet implemented")
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addShippingDataToLocale(item: ShippingDataModel): Completable

    override fun successOnlinePay(
        psn: String,
        allMoney: String,
        tref: String,
        iN: String,
        iD: String,
        takhfif: String,
        takhfifCode: String,
        recivedTime: String,
        receviedAddress: String
    ): Single<SuccessPayOnlineDataModel> {
        TODO("Not yet implemented")
    }
    override fun successOnlineFactorPay(
        psn: String,
        allMoney: String,
        factorSn:String,
        tref: String,
        iN: String,
        iD: String
    ): Single<SuccessPayOnlineDataModel>{
        TODO("Not yet implemented")
    }
}