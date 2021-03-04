package com.thejan.otrium_android.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryTable(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "eventId") var id: Int? = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "languages") var languages: String? = null,
    @ColumnInfo(name = "stars") var stars: Int? = 0
)