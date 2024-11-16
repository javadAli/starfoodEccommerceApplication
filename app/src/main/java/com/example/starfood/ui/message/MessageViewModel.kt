package com.example.starfood.ui.message

import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.repository.messageRepository.MessageRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.message.MessageListDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MessageViewModel(private val repository:MessageRepository):StarFoodViewModel() {
    val messageLiveData = MutableLiveData<MessageListDataModel>()
    fun getMessageList(){
        repository.getMessageList(TokenContainer.psn!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<MessageListDataModel>(compositeDisposable){
                override fun onSuccess(t: MessageListDataModel) {
                    messageLiveData.value = t
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    fun doAddMessage( message:String){
        repository.doAddMessage(TokenContainer.psn!!,message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<MessageListDataModel>(compositeDisposable){
                override fun onSuccess(t: MessageListDataModel) {
                    messageLiveData.value = t
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}