package com.tyme.core.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(entity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(entities: List<UserEntity>): List<Long>

    @Query("SELECT * FROM user_db")
    fun getUserPagingSource(): PagingSource<Int, UserEntity>

    @Query(value = "DELETE FROM user_db")
    fun removeAllUser()

}
