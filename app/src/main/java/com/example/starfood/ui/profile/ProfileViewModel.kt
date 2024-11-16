package com.example.starfood.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.logindatamodel.ConfirmLoginDataModel
import com.example.starfood.datamodel.profile.ProfileDataModel
import com.example.starfood.datamodel.repository.profilerepo.ProfileRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(private val profileRepository: ProfileRepository):StarFoodViewModel() {
    var profileLiveData = MutableLiveData<ProfileDataModel>()

    fun getProfile(psn:String){
        profileRepository.getProfile(psn)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : StarFoodSingleObserver<ProfileDataModel>(compositeDisposable) {
                override fun onSuccess(t: ProfileDataModel) {
                    profileLiveData.value = t
                }
                override fun onError(e: Throwable) {
                }
            })
    }
}