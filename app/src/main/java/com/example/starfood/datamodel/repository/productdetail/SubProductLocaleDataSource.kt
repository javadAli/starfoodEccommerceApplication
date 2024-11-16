package com.example.starfood.datamodel.repository.productdetail

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starfood.dao.ProductLocaleSaved
import com.example.starfood.datamodel.SubmitFavouriteDataModel
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductCategoryDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroup
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductTable
import com.example.starfood.datamodel.detailcategorydatamodel.appendkala.AppendSubKalaDataModel
import com.example.starfood.datamodel.favourite.FavouriteDataModel
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface SubProductLocaleDataSource:SubProductDataSource{
    @Query("SELECT 0 maxSale,secondGroupId,firstGroupId,SecondUnitAmount,1402 FiscalYear,5 CompanyNo,GoodSn,GoodName,UName,Price3,Price4,SnGoodPriceSale,favorite,requested,Amount,bought,callOnSale,SnOrderBYS,BoughtAmount,PackAmount,overLine,secondUnit,freeExistance,activeTakhfifPercent,activePishKharid FROM subProduct_tbl  WHERE mainGrId =:id")
    override fun getProductDetailFromLocale(id: String): Single<List<KalaSubGroup>>
    @Query("SELECT 0 maxSale,secondGroupId,firstGroupId,SecondUnitAmount,1402 FiscalYear,5 CompanyNo,GoodSn,GoodName,UName,Price3,Price4,SnGoodPriceSale,favorite,requested,Amount,bought,callOnSale,SnOrderBYS,BoughtAmount,PackAmount,overLine,secondUnit,freeExistance,activeTakhfifPercent,activePishKharid FROM subProduct_tbl  WHERE secondGroupId =:id")
    override fun getSubGroupProductDetailFromLocale(id: String):Single<List<KalaSubGroup>>
    @Query("SELECT * FROM productLocaleSaved_tbl WHERE goodSn =:goodSn")
    override fun getLocaleCartItem(goodSn: String): Single<ProductLocaleSaved>
    @Query("SELECT * FROM productLocaleSaved_tbl")
    override fun getLocaleData(): Single<List<ProductLocaleSaved>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addProductDetail(item: List<SubProductTable>): Completable
    @Delete
    override fun deleteLocaleData(item: ProductLocaleSaved): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addHeaderProduct(item: List<HeaderDetailCategoryModelItem>): Completable
    @Query("SELECT * FROM productDetailHeader_tbl WHERE selfGroupId =:id")
    override fun getHeaderOfProductDetail(id: String): Single<List<HeaderDetailCategoryModelItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun localeSubmitFavourite(item: FavouriteDataModel): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addLocaleAmountOfProduct(item: AmountProductDataModel): Completable
    @Query("SELECT * FROM favourite_tb")
    override fun localeGetFavourite(): Single<FavouriteDataModel>
    @Query("SELECT * FROM amountProduct_tbl WHERE kalaId =:pCode")
    override fun localeGetAmountOfProduct(pCode: String): Single<AmountProductDataModel>
    @Query("UPDATE kala_tbl set requested=1 WHERE GoodSn=:productId ")
    override fun localeSubmitReminder(productId: String): Completable
    @Query("UPDATE kala_tbl set requested=1 WHERE GoodSn=:gsn")
    override fun localeSubmitCancelReminder(gsn: String): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addLocaleChangesToTable(item: ProductLocaleSaved): Completable
    @Query("UPDATE subProduct_tbl set bought='Yes',BoughtAmount=:boughtAmount,PackAmount=:packAmount WHERE GoodSn=:goodSn")
    override fun changeProductToSold(goodSn: Int,boughtAmount:Int,packAmount:Int):Completable
    override fun submitReminder(customerId: String, productId: String): Single<Int>{
        TODO("Not yet implemented")
    }

    override fun submitCancelReminder(psn: String, gsn: String): Single<String> {
        TODO("Not yet implemented")
    }

    override fun submitFavourite(goodSn: String, psn: String): Single<SubmitFavouriteDataModel>{
        TODO("Not yet implemented")
    }

    override fun getAmountOfProduct(pCode: String, psn: String): Single<AmountProductDataModel>{
        TODO("Not yet implemented")
    }

    override fun getFavourite(psn: String): Single<FavouriteDataModel>{
        TODO("Not yet implemented")
    }
    override fun getProductDetail(id: String, psn: String, page: Int): Single<SubProductCategoryDataModel> {
        TODO("Not yet implemented")
    }


    override fun purchaseRegistration(psn: String, kalaId: String, amountUnit: String): Single<String>{
        TODO("Not yet implemented")
    }
    override fun updateOrder(orderBYSSn: Long, amountUnit: Long, kalaId: Long): Single<String>{
        TODO("Not yet implemented")
    }
    override fun appendSubGroupKala(
        grId: String,
        psn: String,
        subKalaGroupId: String
    ):Single<AppendSubKalaDataModel>{
        TODO("Not yet implemented")
    }
    @Query("DELETE FROM productLocaleSaved_tbl WHERE goodSn=:goodSn")
    override fun deleteLocaleOrder(goodSn: String): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addGroupToLocale(item:List<CategoryDataModelItem>): Completable
}