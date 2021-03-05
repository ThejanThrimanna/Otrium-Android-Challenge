package com.thejan.otrium_android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thejan.otrium_android.database.entities.RepositoryTable
import com.thejan.otrium_android.database.entities.UserTable

@Dao
interface RepositoryDAO {
    @Query("SELECT * FROM repository")
    fun getAll(): List<RepositoryTable>?

    @Query("SELECT * FROM repository WHERE type =:type")
    fun getByType(type: Int): List<RepositoryTable>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repo: RepositoryTable?): Long?

    @Query("DELETE FROM repository")
    fun deleteAll()
}