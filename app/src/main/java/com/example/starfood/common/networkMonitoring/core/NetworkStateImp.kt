package com.example.starfood.common.networkMonitoring.core

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.example.starfood.common.networkMonitoring.Event
import com.example.starfood.common.networkMonitoring.NetworkEvents
import com.example.starfood.common.networkMonitoring.NetworkState

/**
 * This is a static implementation of NetworkState, it holds the network states and is editable but it's only usable from this file.
 */
internal class NetworkStateImp : NetworkState {

    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvents.notify(
                Event.ConnectivityEvent(
                    this
                )
            )
        }

    override var network: Network? = null

    override var linkProperties: LinkProperties? = null
        set(value) {
            val old = field
            field = value
            NetworkEvents.notify(
                Event.LinkPropertyChangeEvent(
                    this,
                    old
                )
            )
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val old = field
            field = value
            NetworkEvents.notify(
                Event.NetworkCapabilityEvent(
                    this,
                    old
                )
            )

        }
}