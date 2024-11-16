package com.example.starfood.datamodel.repository.inviterepo

import com.example.starfood.datamodel.invitefriend.InviteFriendDataModel
import com.example.starfood.datamodel.profit.ProfitDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.Single

class InviteRepoImpl(private val remoteDataSource: InviteRemoteDataSource, private val localDataSource: InviteLocalDataSource) : InviteRepository{

    override fun invite(psn: String): Single<InviteFriendDataModel> = remoteDataSource.invite(psn)
}