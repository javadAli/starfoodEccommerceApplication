package com.example.starfood.datamodel.repository.messageRepository

import com.example.starfood.datamodel.cartitem.CartDataModel
import com.example.starfood.message.MessageListDataModel
import io.reactivex.Single

class MessageLocaleDataSource:MessageDataSource {
    override fun getMessageList(psn: String): Single<MessageListDataModel> {
        TODO("Not yet implemented")
    }
    override fun doAddMessage(psn:String,messageContent:String):Single<MessageListDataModel>{
        TODO("Not yet implemented")
    }
}