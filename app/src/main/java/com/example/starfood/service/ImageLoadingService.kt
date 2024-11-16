package com.example.starfood.service

import com.example.starfood.datamodel.slider.Banner
import com.example.starfood.datamodel.slider.Slider
import com.example.starfood.view.StarFoodImageView

interface ImageLoadingService {
    fun load(imageView:StarFoodImageView, url: String)
}