package com.example.starfood.datamodel.repository.messageRepository

import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.message.MessageListDataModel
import io.reactivex.Single

class MessageRepositoryImpl(private val remoteDataSource: MessageRemoteDataSource, private val localeDataSource: MessageLocaleDataSource):MessageRepository {
    override fun getMessageList(psn: String): Single<MessageListDataModel> = remoteDataSource.getMessageList(psn)
    override fun doAddMessage(psn:String,messageContent:String):Single<MessageListDataModel> = remoteDataSource.doAddMessage(psn,messageContent)

}