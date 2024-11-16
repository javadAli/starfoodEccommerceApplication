package com.example.starfood.datamodel.repository.profit

import com.example.starfood.datamodel.profit.ProfitDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Single

class ProfitRemoteDataSource(val apiService: ApiService):ProfitDataSource {
    override fun profit(psn: String): Single<ProfitDataModel> = apiService.getProfit("Bearer "+TokenContainer.token!!,psn)
}