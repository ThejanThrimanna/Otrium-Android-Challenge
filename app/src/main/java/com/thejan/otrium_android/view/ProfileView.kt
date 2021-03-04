package com.thejan.otrium_android.view

import com.thejan.otrium_android.UserQuery

interface ProfileView {
    fun refreshView(user: UserQuery.Viewer)
}