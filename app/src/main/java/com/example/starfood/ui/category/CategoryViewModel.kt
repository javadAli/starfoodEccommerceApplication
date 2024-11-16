package com.example.starfood.ui.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.starfood.common.StarFoodSingleObserver
import com.example.starfood.common.StarFoodViewModel
import com.example.starfood.datamodel.categorydatamodel.CategoryDataModelItem
import com.example.starfood.datamodel.repository.categoryrepo.CategoryRepository
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryViewModel(categoryRepository: CategoryRepository) : StarFoodViewModel(){
    val categoryLiveData = MutableLiveData<List<CategoryDataModelItem>>()
    init {
        categoryRepository.getCategoryList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : StarFoodSingleObserver<List<CategoryDataModelItem>>(compositeDisposable) {
            override fun onSuccess(t: List<CategoryDataModelItem>){
                categoryLiveData.value = t;
            }
            override fun onError(e: Throwable) {
            }
        })
    }

}