package com.example.starfood.datamodel.repository.lotteryrepo

import com.example.starfood.datamodel.lottery.LotteryDataModel
import io.reactivex.Single

class LotteryRepositoryImpl(private val remoteDataSource: LotteryRemoteDataSource, private val cartLocaleDataSource: LotteryLocaleDataSource):LotteryRepository {
    override fun getLotteryInfo(psn: String): Single<LotteryDataModel> = remoteDataSource.getLotteryInfo(psn)
    override fun setLotteryResult(psn: String, product: String,remainedBonus:Int): Single<String> =remoteDataSource.setLotteryResult(psn,product, remainedBonus)
    override  fun setWeeklyPresentApi(dayPr:String,psn:Int,bonus:Int):Single<Int> = remoteDataSource.setWeeklyPresentApi(dayPr,psn,bonus)
}