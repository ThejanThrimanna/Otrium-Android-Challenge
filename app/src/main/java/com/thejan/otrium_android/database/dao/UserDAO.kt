package com.thejan.otrium_android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thejan.otrium_android.database.entities.UserTable

@Dao
interface UserDAO{
    @Query("SELECT * FROM user")
    fun getAll(): List<UserTable>?

    @Insert
    fun insert(user: UserTable?): Long?

    @Query("DELETE FROM user")
    fun deleteAll()
}