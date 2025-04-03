package com.tyme.core.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_detail_db", indices = [Index(value = ["login"], unique = true)]
)
data class UserDetailEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "login") val username: String = "",
    @ColumnInfo(name = "avatarUrl") val avatarUrl: String = "",
    @ColumnInfo(name = "htmlUrl") val htmlUrl: String = "",
    @ColumnInfo(name = "location") val location: String = "",
    @ColumnInfo(name = "followers") val followers: Long = 0,
    @ColumnInfo(name = "following") val following: Long = 0,
)
