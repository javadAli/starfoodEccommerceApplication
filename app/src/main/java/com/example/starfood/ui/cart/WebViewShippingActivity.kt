package com.example.starfood.ui.cart
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.starfood.R
import com.example.starfood.databinding.ActivityMainBinding
import com.example.starfood.databinding.ActivityWebViewShippingBinding
import com.example.starfood.ui.MainActivity
import com.example.starfood.ui.home.HomeFragment
import com.example.starfood.ui.message.MessageListActivity
import com.google.gson.Gson
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
class WebViewShippingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewShippingBinding
    private lateinit var mainBinding: ActivityMainBinding;
    private val viewModel: WebViewShippingViewModel by viewModel()
    private val sharedPreferences: SharedPreferences by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewShippingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra("myUrl")
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("${url!!}")
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                request?.url?.let { url ->
                    val uri = Uri.parse(url.toString())
                    val status = uri.getQueryParameter("status")
                    if (status == "cancel" || status == "expire") {
                        startActivity(Intent(this@WebViewShippingActivity,MainActivity::class.java))
                        finish()
                        return true // Indicate that you've handled the URL
                    } else {
                        if(!request?.url.toString().isNullOrEmpty()){
                            val tref = "tref"
                            val iN = uri.getQueryParameter("invoiceId")
                            val iD = uri.getQueryParameter("referenceNumber")
                            viewModel.successPaymentResult(
                                sharedPreferences.getString("allMoney", "")!!,
                                tref!!,
                                iN!!,
                                iD!!,
                                sharedPreferences.getString("takhfif", "")!!,
                                sharedPreferences.getString("takhfifCode", "")!!,
                                sharedPreferences.getString("recivedTime", "")!!,
                                sharedPreferences.getString("receviedAddress", "")!!
                            )
                            viewModel.successResultLiveData.observe(this@WebViewShippingActivity){
                                if (it.result == "OK") {
                                    val intent = Intent(
                                        this@WebViewShippingActivity,
                                        OnlineSuccessPayResultActivity::class.java
                                    )
                                    val gson = Gson()
                                    val serializedObject = gson.toJson(it)
                                    intent.putExtra("factor", serializedObject)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@WebViewShippingActivity,
                                        it.result + "ارتباط شما با درگاه بانکی برقرار نشد دوباره تلاش کنید",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                }
                return false
            }
        }
    }
}