package com.tyme.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<RemoteKeyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: RemoteKeyEntity)

    @Query("select * from remote_keys where id=:key")
    suspend fun getKeyByUserList(key: String): RemoteKeyEntity?

    @Query("delete from remote_keys")
    suspend fun clearKeys()
}
