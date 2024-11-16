package com.example.starfood.datamodel.repository.messageRepository

import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.message.MessageListDataModel
import io.reactivex.Single

interface MessageDataSource {
    fun getMessageList(psn:String): Single<MessageListDataModel>
    fun doAddMessage(psn:String,messageContent:String):Single<MessageListDataModel>
}