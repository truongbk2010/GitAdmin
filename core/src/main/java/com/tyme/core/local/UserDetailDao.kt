package com.tyme.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(entity: UserDetailEntity): Long

    @Query(value = "SELECT * FROM user_detail_db WHERE id = :userId")
    fun getUserDetailStream(userId: Long): Flow<UserDetailEntity?>


    @Query(value = "DELETE FROM user_detail_db")
    fun removeAllUser()

}
