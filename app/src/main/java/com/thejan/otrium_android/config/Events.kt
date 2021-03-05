package com.thejan.otrium_android.config

import com.thejan.otrium_android.UserQuery

class Events {
    data class GetSuccessUserEvent(val user: UserQuery.Viewer? = null)
}