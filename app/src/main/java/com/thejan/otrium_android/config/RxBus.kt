package com.thejan.otrium_android.config

interface RxBus {
    fun isRegistered(o: Any): Boolean

    fun register(o: Any)

    fun unregister(o: Any)

    fun post(event: Any)
}