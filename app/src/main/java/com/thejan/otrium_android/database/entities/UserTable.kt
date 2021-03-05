package com.thejan.otrium_android.database.entities

import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserTable(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "eventId") var id: Int? = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "login") var login: String? = null,
    @ColumnInfo(name = "company") var company: String? = null,
    @ColumnInfo(name = "email") var email: String? = null,
    @ColumnInfo(name = "followers") var followers: Int? = 0,
    @ColumnInfo(name = "following)") var following: Int? = 0
)


