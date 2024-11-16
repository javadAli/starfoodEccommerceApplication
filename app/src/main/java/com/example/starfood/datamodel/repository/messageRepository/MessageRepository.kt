package com.example.starfood.datamodel.repository.messageRepository

import com.example.starfood.message.MessageListDataModel
import io.reactivex.Single

interface MessageRepository {
    fun getMessageList(psn:String):Single<MessageListDataModel>
    fun doAddMessage(psn:String,messageContent:String):Single<MessageListDataModel>
}