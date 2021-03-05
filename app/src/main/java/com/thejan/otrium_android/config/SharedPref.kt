package com.thejan.otrium_android.config

import android.content.Context
import com.thejan.otrium_android.GitHubApplication

object SharedPref {
    const val FILE_KEY = "github.profile.file.key"
    const val LAST_UPDATED = "github.profile.file.key"

    fun saveLong(key: String, value: Long) {
        val editor = GitHubApplication.instance.getSharedPreferences(
            FILE_KEY, Context.MODE_PRIVATE
        ).edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun getLong(key: String, defaultval: Long): Long {
        val prefs = GitHubApplication.instance.getSharedPreferences(
            SharedPref.FILE_KEY,
            Context.MODE_PRIVATE
        )
        return prefs.getLong(key, defaultval)
    }

}


