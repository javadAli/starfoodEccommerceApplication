package com.example.starfood.datamodel.repository.profit

import com.example.starfood.datamodel.profit.ProfitDataModel
import io.reactivex.Single

class ProfitRepoImpl(private val remoteDataSource: ProfitRemoteDataSource, private val localDataSource: ProfitLocalDataSource) : ProfitRepository{
    override fun profit(psn: String): Single<ProfitDataModel> = remoteDataSource.profit(psn)
}