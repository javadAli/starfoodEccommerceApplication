package com.example.starfood.datamodel.repository.categoryrepo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.categorydatamodel.HomeAllKalaOfPartDataModel
import com.example.starfood.datamodel.categorydatamodel.SearchDataModelItem
import com.example.starfood.datamodel.homparts.AddBasketHomeDataModel
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.homparts.UpdateBasketFromHomeDataModel
import com.example.starfood.datamodel.slider.SliderDataModel
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface CategoryLocaleDataSource: CategoryDataSource {
    @Query("SELECT * FROM product_tbl")
    override fun getCategoryLocalList(): Single<List<CategoryDataModelItem>>
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    override fun addHomePartsToLocale(item: HomePartsDataModel): Completable
    override fun getSliders(psn: String): Single<SliderDataModel>{
        TODO("Not yet implemented")
    }
    override fun getCategoryList(): Single<List<CategoryDataModelItem>>{
        TODO("Not yet implemented")
    }
    @Query("SELECT * FROM HomeParts_tbl")
    override fun localeGetHomeParts(): Single<HomePartsDataModel>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addProductToTable(product: List<CategoryDataModelItem>): Completable
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    override fun addOrderToLocal(product: List<OrderLocalTable>):Completable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun addSlider(item: SliderDataModel): Completable
    @Query("SELECT * FROM slider_tbl")
    override fun getLocaleSliderToTable(): Single<SliderDataModel>
    override fun searchList(psn: String, name: String): Single<List<SearchDataModelItem>>{
        TODO("Not yet implemented")
    }
    override fun showAllKalaOfHomePart(psn:String, partId:String):Single<HomeAllKalaOfPartDataModel>{
        TODO()
    }
    override fun getHomeParts(psn: String): Single<HomePartsDataModel> {
        TODO("Not yet implemented")
    }
    override fun addToBasketFromHomePage(
        psn: String,
        kalaId: String,
        amountUnit: String
    ): Single<AddBasketHomeDataModel>{
        TODO("Not yet implemented")
    }
    override fun UpdateBasketFromHomePage(
        orderBYSSn: String,
        kalaId: String,
        amountUnit: String
    ): Single<UpdateBasketFromHomeDataModel>{
        TODO("Not yet implemented")
    }
    override fun sendAppTokenToServer(psn: String, token: String): Single<String>{
        TODO("Not yet implemented")
    }
    @Query("SELECT *, 0 AS IsActive, goodSn AS productId, '' AS senonym FROM subProduct_tbl WHERE GoodName LIKE '%' || :name || '%'")
    override fun searchListOffline(name: String): Single<List<SearchDataModelItem>>
    override fun addAssesment(feedbackState: String, feedbackType: String): Single<String>{
        TODO("Not yet implemented")
    }

    override fun checkLogin(): Single<String> {
        TODO("Not yet implemented")
    }
    override fun logout(): Single<String> {
        TODO("Not yet implemented")
    }

    override fun checkButtonAllowance(): Single<String> {
        TODO("Not yet implemented")
    }
}