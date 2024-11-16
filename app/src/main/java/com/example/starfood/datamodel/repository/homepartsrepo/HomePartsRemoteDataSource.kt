package com.example.starfood.datamodel.repository.homepartsrepo

import com.example.starfood.datamodel.brandParts.AllKalaOfBrandDataModel
import com.example.starfood.datamodel.categorydatamodel.HomePartListKalaOfPictureDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Single

class HomePartsRemoteDataSource(private val apiService: ApiService): HomePartsDataSource{
    override fun getAllKalaOfBrands(
        psn: String,
        brandId: String
    ): Single<AllKalaOfBrandDataModel> = apiService.getKalaOfBrand("Bearer "+TokenContainer.token!!,psn,brandId)

    override fun listKalaOfPicture(
        picId: String,
        partId: String,
        psn: String
    ): Single<HomePartListKalaOfPictureDataModel> = apiService.listKalaOfPicture("Bearer "+TokenContainer.token!!,picId,partId,psn)
}