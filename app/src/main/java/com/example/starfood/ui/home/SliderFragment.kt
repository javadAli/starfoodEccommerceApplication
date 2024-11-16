package com.example.starfood.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.starfood.R
import com.example.starfood.common.EXTRA_KEY_DATA
import com.example.starfood.service.ImageLoadingService
import com.example.starfood.view.StarFoodImageView
import org.koin.android.ext.android.inject

class SliderFragment:Fragment() {
    private val imageLoading:ImageLoadingService by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imageView =
            inflater.inflate(R.layout.fragment_slider, container, false) as StarFoodImageView
        val slider = requireArguments().getString(EXTRA_KEY_DATA)?: throw IllegalStateException(
                "Banner cannot be null"
            )
        imageLoading.load(imageView,"https://starfoods.ir/resources/assets/images/mainSlider/${slider}")
        return imageView
    }
    companion object{
        fun newInstance(slider: String):SliderFragment{
            return SliderFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_KEY_DATA, slider)
                }
            }
        }
    }
}
