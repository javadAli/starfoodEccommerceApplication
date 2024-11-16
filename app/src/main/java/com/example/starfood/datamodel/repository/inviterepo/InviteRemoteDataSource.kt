package com.example.starfood.datamodel.repository.inviterepo

import com.example.starfood.datamodel.invitefriend.InviteFriendDataModel
import com.example.starfood.datamodel.profit.ProfitDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.service.ApiService
import io.reactivex.Single

class InviteRemoteDataSource(val apiService: ApiService):InviteDataSource {
    override fun invite(psn: String): Single<InviteFriendDataModel> = apiService.inviteFriend(
        "Bearer "+TokenContainer.token!!,psn)
}