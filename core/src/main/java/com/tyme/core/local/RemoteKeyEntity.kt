package com.tyme.core.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "remote_keys",
)
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextSinceKey: Long?,
    val lastUpdated: Long,
)
