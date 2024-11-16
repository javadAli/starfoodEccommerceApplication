package com.example.starfood.ui.login

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import com.example.starfood.common.IS_LOGIN
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.databinding.ActivityLoginBinding
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

//used for checking internet connection
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.starfood.R
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.ui.MainActivity
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : StarFoodActivity() {
     lateinit var  binding : ActivityLoginBinding
     val loginViewModel: LoginViewModel by viewModel()
     val sharedPreferences: SharedPreferences by inject()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val scaleX = ObjectAnimator.ofFloat(it, "scaleX", 1f, 1.1f, 1f)
            val scaleY = ObjectAnimator.ofFloat(it, "scaleY", 1f, 1.1f, 1f)
            scaleX.duration = 500
            scaleY.duration = 500
            // Start animations together
            scaleX.start()
            scaleY.start()
            lifecycleScope.launch {
                if (InternetCheck.isNetworkAvailable(this@LoginActivity) && InternetCheck.isInternetReachable()) {
                    if (binding.phone.text.isEmpty() || binding.password.text.isEmpty()){
                        val emptyToast:Toast=Toast.makeText(applicationContext," شماره موبایل یا رمز عبور خالی می باشد", Toast.LENGTH_LONG)
                        emptyToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 100)
                        emptyToast.show()
                    }else{
                        loginViewModel.getLogin(binding.phone.text.toString(),binding.password.text.toString())
                        loginViewModel.userInfoLiveData.observe(this@LoginActivity) {
                            if (it.status == 200){
                                if(it.loginFlag=="Confirm") {
                                    val gson = Gson()
                                    val serializedObject = gson.toJson(it)
                                    val intent = Intent(this@LoginActivity, ConfirmLoginActivity::class.java)
                                    intent.putExtra("platformList", serializedObject)
                                    TokenContainer.tokenUpdate(it.token)
                                    sharedPreferences.edit().apply {
                                        putInt(IS_LOGIN, 1)
                                        putString("token",it.token)
                                        putString("message_data", "")
                                        putString("message_title", "")
                                    }.apply()
                                    startActivity(intent)
                                    finish()
                                }
                                if(it.loginFlag=="LoggedIn") {
                                    val gson = Gson()
                                    val serializedObj = gson.toJson(it)
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    TokenContainer.tokenUpdate(it.token)
                                    sharedPreferences.edit().apply {
                                        putInt(IS_LOGIN, 1)
                                        putString("token",it.token)
                                        putString("message_data", "")
                                        putString("message_title", "")
                                    }.apply()
                                    intent.putExtra("platformList",serializedObj)
                                    startActivity(intent)
                                    finish()
                                }
                                if(it.loginFlag=="MoarrifCode"){
                                    val gson = Gson()
                                    val serializedObj = gson.toJson(it)
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                    intent.putExtra("platformList",serializedObj)
                                    sharedPreferences.edit().apply {
                                        putInt(IS_LOGIN, 1)
                                        putString("token",it.token)
                                        putString("message_data", "")
                                        putString("message_title", "")
                                    }.apply()
                                    startActivity(intent)
                                    finish()
                                }
                                if(it.loginFlag=="NotAllowed"){
                                    val notAllowdToast:Toast=Toast.makeText(this@LoginActivity,"با پشتیبانی شرکت تماس بگیرید!",
                                        Toast.LENGTH_LONG)
                                    notAllowdToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 100)
                                    notAllowdToast.show()
                                }
                            }else{
                                val wrongPassToast:Toast=Toast.makeText(this@LoginActivity," شماره موبایل یا رمز عبور وارد شده صحیح نمی باشد",
                                    Toast.LENGTH_LONG)
                                wrongPassToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 100)
                                wrongPassToast.show()
                            }
                        }
                    }
                } else {
                    val noNetToast:Toast=Toast.makeText(this@LoginActivity,"عدم دسترسی به اینترنت.. ", Toast.LENGTH_LONG)
                    noNetToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 100)
                    noNetToast.show()
                }
            }
        }
        val contactTextView: TextView = findViewById(R.id.contact_admin)
        val contactTextViewOffice: TextView = findViewById(R.id.contact_poshtiban)

        contactTextView.setOnClickListener {
            val phoneNumber = "02148286" // Phone number to dial
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }
        contactTextViewOffice.setOnClickListener {
            val phoneNumber = "02149973000" // Phone number to dial
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }
    }
}

object InternetCheck{
    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    suspend fun isInternetReachable(): Boolean = withContext(Dispatchers.IO){
        try {
            val connection = URL("https://www.google.com").openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent", "Android")
            connection.connectTimeout = 1500
            connection.connect()
            connection.responseCode == 200
        } catch (e: Exception) {
            false
        }
    }
}