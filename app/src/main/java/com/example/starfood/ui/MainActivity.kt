package com.example.starfood.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.starfood.R
import com.example.starfood.common.StarFoodActivity
import com.example.starfood.common.networkMonitoring.NetworkStateHolder
import com.example.starfood.databinding.ActivityMainBinding
import com.example.starfood.datamodel.repository.userlogin.TokenContainer
import com.example.starfood.ui.cart.CartFragment
import com.example.starfood.ui.category.CategoryFragment
import com.example.starfood.ui.favourite.FavouriteActivity
import com.example.starfood.ui.home.HomeFragment
import com.example.starfood.ui.login.LoginActivity
import com.example.starfood.ui.message.MessageListActivity
import com.example.starfood.ui.profile.ProfileActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
class MainActivity : StarFoodActivity(){
     val sharedPreferences :SharedPreferences by inject()
     lateinit var binding: ActivityMainBinding
     val viewModel:MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val messageBody: String? = getMessageData().get(0)
        val messageTitle: String? = getMessageData().get(1)
        if (messageBody != null) {
            if (messageBody.length > 3) {
                AlertDialog.Builder(this@MainActivity)
                .setTitle("" + messageTitle + "")
                .setMessage("" + messageBody + "")
                .setIcon(com.example.starfood.R.drawable.starfood)
                .setCancelable(true)
                .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                    val context = applicationContext
                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                    val editor = sharedPreferences.edit()
                    editor.putString("message_data", "")
                    editor.putString("message_title", "")
                    editor.apply()
                }).show()
            }
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            val token = task.result
            viewModel.sendAppTokenToServer(token);
        })

        val navigationView: NavigationView = findViewById(com.example.starfood.R.id.navigationView)
        if(NetworkStateHolder.isConnected) {
            viewModel.checkButtonAllowance()
            viewModel.buttonAllowance.observe(this) {
                if (it.toInt() == 1) {
                    var isVisible = 1
                    val menu = navigationView.menu
                    val exitItem = menu.findItem(R.id.exit)
                    exitItem.isVisible = (isVisible == 1)
                } else {
                    var isVisible = 0
                    val menu = navigationView.menu
                    val exitItem = menu.findItem(R.id.exit)
                    exitItem.isVisible = (isVisible == 1)
                }
            }
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation item clicks here
            when (menuItem.itemId) {
                com.example.starfood.R.id.exit ->{
                    viewModel.logout()
                    sharedPreferences.edit().apply {
                        clear()
                    }.apply()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                com.example.starfood.R.id.category->{
                    binding.include.bottomNavigationMain.selectedItemId = com.example.starfood.R.id.category
                    replaceFragment(CategoryFragment())
                }
                com.example.starfood.R.id.cart->{
                    binding.include.bottomNavigationMain.selectedItemId = com.example.starfood.R.id.cart
                    replaceFragment(CartFragment())
                }
                com.example.starfood.R.id.profile->{
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                com.example.starfood.R.id.favourite->{
                    startActivity(Intent(this,FavouriteActivity::class.java))
                }
                com.example.starfood.R.id.connection->{
                    startActivity(Intent(this,ConnectUsActivity::class.java))
                }
                com.example.starfood.R.id.message->{
                    startActivity(Intent(this,MessageListActivity::class.java))
                }
                com.example.starfood.R.id.appAsses->{
                    if(NetworkStateHolder.isConnected) {
                        val builder = this?.let { androidx.appcompat.app.AlertDialog.Builder(it) }
                        if (builder != null) {
                            builder.setTitle("نظر سنجی کارکرد برنامه")
                                .setMessage("آیا از کارکرد برنامه رازی هستید؟")
                                .setPositiveButton("بله") { dialog: DialogInterface, _ ->
                                    viewModel.addAssesment("1", "2")
                                    dialog.dismiss()

                                }.setNegativeButton("خیر") { dialog: DialogInterface, _: Int ->
                                    viewModel.addAssesment("0", "2")
                                    dialog.dismiss()
                                }.show()
                            viewModel.assmentLiveData.observe(this) {
                                val builder2 = this?.let { androidx.appcompat.app.AlertDialog.Builder(it) }
                                if (builder2 != null) {
                                    builder2.setMessage("ممنون از اینکه نظر دادید!")
                                    .setIcon(R.drawable.star)
                                    .setTitle("استارفود")
                                    .setPositiveButton("oke") { dialog: DialogInterface, _ ->
                                        dialog.dismiss()
                                    }.show()
                                }
                            }
                        }
                    }else{
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle("عدم اتصال")
                            .setMessage("ابتدا به اینترنت متصل شوید")
                            .setIcon(com.example.starfood.R.drawable.starfood)
                            .setCancelable(true)
                            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                                val context = applicationContext
                            }).show()
                    }
                }
                com.example.starfood.R.id.upload->{
                    if(NetworkStateHolder.isConnected) {
                        viewModel.getCategory()
                        viewModel.categoryResultLiveData.observe(this) {
                            it.forEach {
                                viewModel.getSubProductList(it.id, TokenContainer.psn!!)
                            }
                        }
                    }else{
                        AlertDialog.Builder(this@MainActivity)
                        .setTitle("عدم اتصال")
                        .setMessage("ابتدا به اینترنت متصل شوید")
                        .setIcon(com.example.starfood.R.drawable.starfood)
                        .setCancelable(true)
                        .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                            val context = applicationContext
                        }).show()
                    }
                }
            }
            binding.drawerLayout.closeDrawers()
            true
        }

        binding.include.bottomNavigationMain.setOnNavigationItemSelectedListener {
            menuItem ->
            when (menuItem.itemId){
                com.example.starfood.R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                com.example.starfood.R.id.category -> {
                    // Handle item 2 click
                    replaceFragment(CategoryFragment())
                    true
                }
                com.example.starfood.R.id.cart -> {
                    // Handle item 3 click
                    replaceFragment(CartFragment())
                    true
                }
                com.example.starfood.R.id.person -> {
                    // Handle item 4 click
                    // open the side navigation bar
                    openSideNavigationBar()
                    true
                }
                else -> false
            }
        }
        binding.include.bottomNavigationMain.selectedItemId = com.example.starfood.R.id.home
    }
    private fun openSideNavigationBar() {
        // Assuming you have a DrawerLayout and NavigationView set up
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(com.example.starfood.R.id.fragment_container, fragment).commit()
    }
    private fun getMessageData(): Array<String?> {
        val context = applicationContext
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return arrayOf(
            sharedPreferences.getString("message_data", ""),
            sharedPreferences.getString("message_title", "")
        )
    }
override fun onBackPressed() {
    super.onBackPressed()
}

}