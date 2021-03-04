package com.thejan.otrium_android.config

import com.thejan.otrium_android.UserQuery

interface ProfileView {
    fun refreshView(user: UserQuery.Viewer)
}