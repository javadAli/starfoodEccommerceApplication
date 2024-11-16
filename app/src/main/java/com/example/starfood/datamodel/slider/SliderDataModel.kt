package com.example.starfood.datamodel.slider

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.starfood.datamodel.cartitem.ConverterStringDataModel

@Entity("slider_tbl")
data class SliderDataModel(
    @PrimaryKey(autoGenerate = false)
    @TypeConverters(ConverterStringDataModel::class)
    val bigSliderList: List<String>,
    @TypeConverters(ConverterSliderDataModel::class)
    val sliders: List<Slider>,
    @TypeConverters(ConverterStringDataModel::class)
    val smalSliderList: List<String>,
    @TypeConverters(ConverterSmallSliderDataModel::class)
    val smallSlider: List<SmallSlider>
)