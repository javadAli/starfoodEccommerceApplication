package com.example.starfood.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.logindatamodel.LoginResponse
import com.example.starfood.datamodel.repository.userlogin.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel( val userRepository: UserRepository): StarFoodViewModel(){
    val userInfoLiveData = MutableLiveData<LoginResponse>()
    fun getLogin(email:String, password:String){
        userRepository.getLoginInfo(email,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<LoginResponse>(compositeDisposable) {
                override fun onSuccess(t: LoginResponse) {
                    userInfoLiveData.value = t
                    userRepository.onSuccessfulLogin(t.username,t,t.psn)
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}