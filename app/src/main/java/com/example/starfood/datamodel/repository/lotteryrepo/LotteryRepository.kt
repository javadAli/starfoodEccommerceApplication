package com.example.starfood.datamodel.repository.lotteryrepo
import com.example.starfood.datamodel.lottery.LotteryDataModel
import io.reactivex.Single
interface LotteryRepository {
    fun getLotteryInfo(psn:String):Single<LotteryDataModel>
    fun setLotteryResult(psn: String, product: String,remainedBonus:Int): Single<String>

    fun setWeeklyPresentApi(dayPr:String,psn:Int,bonus:Int):Single<Int>
}