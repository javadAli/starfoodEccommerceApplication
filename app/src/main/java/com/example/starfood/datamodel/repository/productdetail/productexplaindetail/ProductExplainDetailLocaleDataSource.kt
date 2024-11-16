package com.example.starfood.datamodel.repository.productdetail.productexplaindetail
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface ProductExplainDetailLocaleDataSource:ProductExplainDetailDataSource{
    override fun getProductExplain(groupId: String, psn: String, id: String): Single<ProductExplainDataModel>{ TODO("Not yet implemented") }
    @Query("SELECT * FROM ProductExplain_tbl WHERE GoodSn=:id AND mainGroupId=:groupId ")
    override fun localeGetProductExplain(groupId: String, id: String): Single<ProductExplainDataModel>
    @Query("SELECT BoughtAmount Amount,Amount AmountExist,1402 FiscalYear,0 GoodCde,GoodName,GoodSn,' ' NameGRP,SecondUnitAmount,ifnull(PackAmount,0)PackAmount,Price3,Price4,ifnull(SnOrderBYS,0)SnOrderBYS,UName AS UNAME,activePishKharid,activeTakhfifPercent,'[]' AS assameKala,bought,callOnSale,currency,currencyName,GoodName descKala,favorite,freeExistance,'' groupName,logoPos,firstGroupId mainGroupId,43 maxSale,overLine,0 preBought,requested,secondUnit FROM subProduct_tbl  WHERE GoodSn=:id")
    override fun getProductExplainLocal(id: String): Single<ProductExplainDataModel>
    @Update
    override fun updateExplainProduct(item: ProductExplainDataModel): Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addExplainToTable(item: ProductExplainDataModel): Completable
}