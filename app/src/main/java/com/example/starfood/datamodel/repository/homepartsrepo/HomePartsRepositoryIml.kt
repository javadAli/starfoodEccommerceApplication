package com.example.starfood.datamodel.repository.homepartsrepo
import com.example.starfood.datamodel.brandParts.AllKalaOfBrandDataModel
import com.example.starfood.datamodel.categorydatamodel.HomePartListKalaOfPictureDataModel
import io.reactivex.Single
class HomePartsRepositoryIml(private val remote: HomePartsRemoteDataSource):HomePartsRepository {
    override fun getAllKalaOfBrands(psn: String, brandId: String): Single<AllKalaOfBrandDataModel> = remote.getAllKalaOfBrands(psn,brandId)
    override fun listKalaOfPicture(picId: String,partId: String,psn: String): Single<HomePartListKalaOfPictureDataModel> = remote.listKalaOfPicture(picId,partId,psn)
}