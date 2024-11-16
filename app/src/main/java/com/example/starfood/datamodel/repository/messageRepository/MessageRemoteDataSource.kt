package com.example.starfood.datamodel.repository.messageRepository

import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.message.MessageListDataModel
import com.example.starfood.service.ApiService
import io.reactivex.Single

class MessageRemoteDataSource(private val apiService: ApiService):MessageDataSource{
    override fun getMessageList(psn: String): Single<MessageListDataModel> = apiService.getMessageList(
        "Bearer "+TokenContainer.token!!,psn)
    override fun doAddMessage(psn:String,messageContent:String):Single<MessageListDataModel> = apiService.doAddMessage("Bearer "+TokenContainer.token!!,psn,messageContent)
}