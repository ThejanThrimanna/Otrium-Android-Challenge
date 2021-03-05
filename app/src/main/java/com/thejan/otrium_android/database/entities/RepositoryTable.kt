package com.thejan.otrium_android.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryTable(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long? = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "languages") var languages: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "type") var type: Int? = 0,
    @ColumnInfo(name = "companyName") var companyName: String? = null,
    @ColumnInfo(name = "stars") var stars: Int? = 0
)