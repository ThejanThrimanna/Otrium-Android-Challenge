package com.thejan.otrium_android.config

import org.greenrobot.eventbus.EventBus

class AppBus(private val eventBus: EventBus): RxBus {

    override fun isRegistered(o: Any): Boolean {
        return eventBus.isRegistered(o)
    }

    override fun register(o: Any) {
        eventBus.register(o)
    }

    override fun unregister(o: Any) {
        eventBus.unregister(o)
    }

    override fun post(event: Any) {
        eventBus.post(event)
    }
}