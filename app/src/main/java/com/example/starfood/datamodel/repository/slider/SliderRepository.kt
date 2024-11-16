package com.example.starfood.datamodel.repository.slider

import com.example.starfood.datamodel.slider.SliderDataModel
import io.reactivex.Single

interface SliderRepository {
    fun getSliders(psn:String): Single<SliderDataModel>
}