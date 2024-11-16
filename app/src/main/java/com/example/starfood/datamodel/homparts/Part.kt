package com.example.starfood.datamodel.homparts

data class Part(
    val PartPriority: String,
    val allBrands: List<AllBrand>,
    val allGroups: List<AllGroup>,
    val allKalas: List<AllKala>,
    val homepartId: String,
    val partColor: String,
    val partId: String,
    val partType: String,
    val pictures: List<Picture>,
    val showAll: String,
    val showOverLine: String,
    val showPercentTakhfif: String,
    val showTedad: String,
    val textColor: String,
    val textFontSize: String,
    val textLogo: String,
    val title: String
)