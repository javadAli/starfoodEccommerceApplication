package com.example.starfood.common.networkMonitoring

import android.app.Application
import android.content.Context
import android.net.*
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.starfood.common.networkMonitoring.core.ActivityLifecycleCallbacksImp
import com.example.starfood.common.networkMonitoring.core.NetworkCallbackImp
import com.example.starfood.common.networkMonitoring.core.NetworkStateImp
import com.example.starfood.ui.App


object NetworkStateHolder : NetworkState {

    private lateinit var holder: NetworkStateImp


    override val isConnected: Boolean
        get() = holder.isConnected
    override val network: Network?
        get() = holder.network
    override val networkCapabilities: NetworkCapabilities?
        get() = holder.networkCapabilities
    override val linkProperties: LinkProperties?
        get() = holder.linkProperties


    /**
     * This starts the broadcast of network events to NetworkState and all Activity implementing NetworkConnectivityListener
     * @see NetworkState
     * @see NetworkConnectivityListener
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun App.registerConnectivityBroadcaster() {

        holder = NetworkStateImp()
        //register tje Activity Broadcaster
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImp(holder))


        //get connectivity manager
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //register to network events
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), NetworkCallbackImp(holder))

    }


}