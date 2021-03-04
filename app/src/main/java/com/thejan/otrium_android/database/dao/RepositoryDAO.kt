package com.thejan.otrium_android.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.thejan.otrium_android.database.entities.RepositoryTable
import com.thejan.otrium_android.database.entities.UserTable

interface RepositoryDAO{
    @Query("SELECT * FROM repository")
    fun getAll(): List<RepositoryTable>?

    @Insert
    fun insert(repo: RepositoryTable?): Long?

    @Query("DELETE FROM repository")
    fun deleteAll()
}