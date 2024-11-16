package com.example.starfood.datamodel.lottery

data class LotteryDataModel(
    val allBonus: Int,
    val lotteryMinBonus: String,
    val presentInfo: List<PresentInfo>,
    val products: List<Product>,
    val todayDate: TodayDate
)