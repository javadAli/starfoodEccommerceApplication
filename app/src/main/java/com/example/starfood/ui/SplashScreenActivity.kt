package com.example.starfood.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.example.starfood.R
import com.example.starfood.common.IS_LOGIN
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.ui.login.LoginActivity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject

class SplashScreenActivity : StarFoodActivity(){
    val sharedPreferences:SharedPreferences by inject()
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        onLoginEvent()
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoginEvent(){

        val timer = object : CountDownTimer(1500,100000){
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (sharedPreferences.getInt(IS_LOGIN,0)==1){
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    handler.postDelayed({

                        val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    },1000)
                }
            }
        }
        timer.start()
    }
}