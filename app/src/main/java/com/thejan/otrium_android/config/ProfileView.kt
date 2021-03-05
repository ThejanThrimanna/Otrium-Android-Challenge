package com.thejan.otrium_android.config

import com.thejan.otrium_android.database.entities.RepositoryTable
import com.thejan.otrium_android.database.entities.UserTable

interface ProfileView {
    fun refreshView(
        user: List<UserTable>?,
        pinedList: List<RepositoryTable>,
        topRepoList: List<RepositoryTable>,
        starredRepoList: List<RepositoryTable>
    )
}