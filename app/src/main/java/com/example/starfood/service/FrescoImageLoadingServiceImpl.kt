package com.example.starfood.service

import com.example.starfood.datamodel.slider.Slider
import com.example.starfood.view.StarFoodImageView
import com.facebook.drawee.view.SimpleDraweeView
import java.lang.IllegalStateException

class FrescoImageLoadingServiceImpl:ImageLoadingService{
    override fun load(imageView: StarFoodImageView, url: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(url)
        else IllegalStateException("imageview must be instance of SimpleDraweeView")
    }

}