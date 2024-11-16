package com.example.starfood.datamodel.repository.slider

import com.example.starfood.datamodel.slider.SliderDataModel
import io.reactivex.Single

class SliderRepositoryImpl(private val sliderRemoteDataSource: SliderRemoteDataSource):SliderRepository{
    override fun getSliders(psn:String): Single<SliderDataModel> = sliderRemoteDataSource.getSliders(psn)
}