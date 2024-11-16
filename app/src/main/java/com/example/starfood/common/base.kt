package com.example.starfood.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starfood.R
import io.reactivex.disposables.CompositeDisposable

abstract class StarFoodFragment: Fragment(), StarFoodView{
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context
/*
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }*/

}
abstract class StarFoodActivity: AppCompatActivity(), StarFoodView{
    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup
            if (viewGroup !is CoordinatorLayout) {
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }
                throw IllegalStateException("RootView must be instance of CoordinatorLayout")
            } else
                return viewGroup
        }
    override val viewContext: Context?
        get() = this
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }*/

}
interface StarFoodView{
    val rootView: CoordinatorLayout?
    val viewContext:Context?
    fun setProgressIndicator(mustShow:Boolean){
        rootView?.let {
            viewContext?.let {context->
                var loadingView =it.findViewById<View>(R.id.loadingView)
                if (loadingView == null && mustShow){
                    loadingView = LayoutInflater.from(context).inflate(R.layout.loading_view,it,false)
                    it.addView(loadingView)
                }
                loadingView?.visibility= if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
}
abstract class StarFoodViewModel : ViewModel(){
    val compositeDisposable = CompositeDisposable()
    val progressLiveData = MutableLiveData<Boolean>()
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}