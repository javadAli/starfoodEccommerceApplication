package com.example.starfood.common.networkMonitoring

import androidx.lifecycle.LiveData
import com.example.starfood.common.networkMonitoring.Event

/**
 * This liveData enabling network connectivity monitoring
 * @see NetworkStateHolder to get current connection state
 */
object NetworkEvents : LiveData<Event>() {

    internal fun notify(event: Event) {
        postValue(event)
    }

}