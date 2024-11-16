package com.example.starfood.datamodel.repository.profit

import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.profit.ProfitDataModel
import io.reactivex.Single

interface ProfitRepository {
    fun profit(psn:String):Single<ProfitDataModel>
}