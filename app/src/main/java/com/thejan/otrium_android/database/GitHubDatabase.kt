package com.thejan.otrium_android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thejan.otrium_android.database.dao.RepositoryDAO
import com.thejan.otrium_android.database.dao.UserDAO
import com.thejan.otrium_android.database.entities.RepositoryTable
import com.thejan.otrium_android.database.entities.UserTable

@Database(
    entities = [UserTable::class, RepositoryTable::class],
    version = 5
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun reposDAO(): RepositoryDAO?
    abstract fun userDAO(): UserDAO?
}