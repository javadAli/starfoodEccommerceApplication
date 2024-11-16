package com.example.starfood.datamodel.repository.inviterepo

import com.example.starfood.datamodel.invitefriend.InviteFriendDataModel
import com.example.starfood.datamodel.profit.ProfitDataModel
import io.reactivex.Single

interface InviteDataSource {
    fun invite(psn:String):Single<InviteFriendDataModel>
}