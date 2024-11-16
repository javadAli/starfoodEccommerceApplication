package com.example.starfood.datamodel.repository.homepartsrepo

import com.example.starfood.datamodel.brandParts.AllKalaOfBrandDataModel
import com.example.starfood.datamodel.categorydatamodel.HomePartListKalaOfPictureDataModel
import io.reactivex.Single
interface HomePartsDataSource {
    fun getAllKalaOfBrands(psn:String,brandId:String): Single<AllKalaOfBrandDataModel>
    fun listKalaOfPicture(picId:String,partId:String,psn:String): Single<HomePartListKalaOfPictureDataModel>
}