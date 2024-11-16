package com.example.starfood.datamodel.repository.lotteryrepo

import com.example.starfood.datamodel.lottery.LotteryDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Single
import retrofit2.Call

class LotteryRemoteDataSource(private val apiService: ApiService):LotteryDataSource{
    override fun getLotteryInfo(psn: String): Single<LotteryDataModel> = apiService.getLotteryInfo("Bearer "+TokenContainer.token!!,psn)
    fun setLotteryResult(psn: String,product:String,remainedBonus:Int): Single<String> = apiService.setLotteryResult("Bearer "+TokenContainer.token!!,psn,product, remainedBonus)
    fun setWeeklyPresentApi(dayPr:String,psn:Int,bonus:Int):Single<Int> =apiService.setWeeklyPresentApi("Bearer "+TokenContainer.token!!,dayPr,psn,bonus)
}