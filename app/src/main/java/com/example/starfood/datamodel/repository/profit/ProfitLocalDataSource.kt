package com.example.starfood.datamodel.repository.profit

import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profit.ProfitDataModel
import io.reactivex.Single

class ProfitLocalDataSource:ProfitDataSource {

    override fun profit(psn: String): Single<ProfitDataModel> {
        TODO("Not yet implemented")
    }
}