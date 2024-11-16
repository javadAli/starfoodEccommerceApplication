package com.example.starfood.datamodel.repository.lotteryrepo

import com.example.starfood.datamodel.lottery.LotteryDataModel
import io.reactivex.Single

interface LotteryDataSource {
    fun getLotteryInfo(psn:String):Single<LotteryDataModel>
}