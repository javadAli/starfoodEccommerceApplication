package com.example.starfood.datamodel.repository.lotteryrepo

import com.example.starfood.datamodel.lottery.LotteryDataModel
import io.reactivex.Single

class LotteryLocaleDataSource:LotteryDataSource {
    override fun getLotteryInfo(psn: String): Single<LotteryDataModel> {
        TODO("Not yet implemented")
    }
}