package com.example.starfood.datamodel.repository.slider

import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.datamodel.slider.SliderDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Single

class SliderRemoteDataSource(private val apiService: ApiService):SliderDataSource{
    override fun getSliders(psn:String): Single<SliderDataModel> = apiService.getSliders(
        "Bearer "+TokenContainer.token!!,psn)
}