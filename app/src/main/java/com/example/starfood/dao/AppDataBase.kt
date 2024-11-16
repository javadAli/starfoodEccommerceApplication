package com.example.starfood.dao

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starfood.datamodel.amountofproduct.AmountProductDataModel
import com.example.starfood.datamodel.cartitem.ConverterOrderDataModel
import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.cartitem.ConverterStringDataModel
import com.example.starfood.datamodel.cartitem.CartOrder
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.ConverterGroupsDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.ConverterKalaDataModel
import com.example.starfood.datamodel.detailcategorydatamodel.GroupsTable
import com.example.starfood.datamodel.detailcategorydatamodel.HeaderDetailCategoryModelItem
import com.example.starfood.datamodel.detailcategorydatamodel.KalaSubGroupTable
import com.example.starfood.datamodel.detailcategorydatamodel.SubProductTable
import com.example.starfood.datamodel.favourite.FavouriteConverterDataModel
import com.example.starfood.datamodel.favourite.FavouriteDataModel
import com.example.starfood.datamodel.homparts.ConverterHomePart
import com.example.starfood.datamodel.homparts.HomePartsDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ConverterAsSameKalaDataModel
import com.example.starfood.datamodel.productexplaindatamodel.ProductExplainDataModel
import com.example.starfood.datamodel.repository.cartrepository.CartLocaleDataSource
import com.example.starfood.datamodel.repository.categoryrepo.CategoryLocaleDataSource
import com.example.starfood.datamodel.repository.productdetail.SubProductLocaleDataSource
import com.example.starfood.datamodel.repository.productdetail.productexplaindetail.ProductExplainDetailLocaleDataSource
import com.example.starfood.datamodel.repository.shipping.ShippingLocalDataSource
import com.example.starfood.datamodel.repository.updateorder.UpdateOrderLocalDataSource
import com.example.starfood.datamodel.shipping.ShippingAddressConverter
import com.example.starfood.datamodel.shipping.ShippingCustomerConverter
import com.example.starfood.datamodel.shipping.ShippingDataModel
import com.example.starfood.datamodel.shipping.ShippingSettingConverter
import com.example.starfood.datamodel.slider.ConverterSliderDataModel
import com.example.starfood.datamodel.slider.ConverterSmallSliderDataModel
import com.example.starfood.datamodel.slider.SliderDataModel

@Database(version = 1 , exportSchema = true ,entities = [ CategoryDataModelItem::class,CartDataModel::class,SliderDataModel::class
                                                            ,HeaderDetailCategoryModelItem::class,FavouriteDataModel::class
                                                            ,AmountProductDataModel::class, ProductExplainDataModel::class
                                                            ,ProductLocaleSaved::class,CartOrder::class,CartFactorLocaleDataBase::class, ShippingDataModel::class
                                                            ,HomePartsDataModel::class,SubProductTable::class,GroupsTable::class
                                                            ,KalaSubGroupTable::class])
@TypeConverters(ConverterOrderDataModel::class,ConverterStringDataModel::class,ConverterSliderDataModel::class,ConverterSmallSliderDataModel::class
,ConverterGroupsDataModel::class,ConverterKalaDataModel::class,FavouriteConverterDataModel::class, ConverterAsSameKalaDataModel::class,
    ShippingAddressConverter::class, ShippingCustomerConverter::class, ShippingSettingConverter::class,ConverterHomePart::class)

 abstract class AppDataBase:RoomDatabase(){
    abstract fun categoryDao() : CategoryLocaleDataSource
    abstract fun updateOrderDao() : UpdateOrderLocalDataSource
    abstract fun cartDao() : CartLocaleDataSource
    abstract fun productDetailDao() : SubProductLocaleDataSource
    abstract fun exPlainOfProduct() : ProductExplainDetailLocaleDataSource
    abstract fun shippingDao() : ShippingLocalDataSource
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "db_app" // Name of your database
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

