package com.tyme.core.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tyme.core.local.AppDatabase
import com.tyme.core.local.RemoteKeyDao
import com.tyme.core.local.RemoteKeyEntity
import com.tyme.core.local.UserDao
import com.tyme.core.local.UserEntity
import com.tyme.core.mapper.toEntity
import com.tyme.core.network.UserService
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class UserPagingRemoteMediator(
    private val database: AppDatabase,
    private val userService: UserService,
    private val userDao: UserDao,
    private val remoteKeyDao: RemoteKeyDao,
) : RemoteMediator<Int, UserEntity>() {
    /**
     *  Config the strategy while fetching new data
     */
    override suspend fun initialize(): InitializeAction {
        val remoteKey =
            remoteKeyDao.getKeyByUserList(KEY_USER_LIST)
                ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.MINUTES.convert(10, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>,
    ): MediatorResult {
        try {
            // Determine the page should be fetched data
            val sinceKey =
                when (loadType) {
                    LoadType.REFRESH -> FIRST_SINCE

                    LoadType.PREPEND ->  {
                        return MediatorResult.Success(true)
                    }

                    LoadType.APPEND -> {
                        val remoteKey =
                            remoteKeyDao.getKeyByUserList(KEY_USER_LIST)
                                ?: return MediatorResult.Success(true)

                        if (remoteKey.nextSinceKey == null) {
                            return MediatorResult.Success(true)
                        }
                        remoteKey.nextSinceKey
                    }
                }

            val result =
                userService.getAllUsers(
                    pageSize = DEFAULT_PAGE_SIZE,
                    since = sinceKey
                )

            val listUsers = result.body().orEmpty()
            database.withTransaction {
                // If refresh data, force remove all cache data
                if (loadType == LoadType.REFRESH) {
                    userDao.removeAllUser()
                }

                val nextSinceKey = if (listUsers.isEmpty()) null else listUsers.last().id + 1

                val userEntities = listUsers.map {
                        it.toEntity()
                    }
                // Update next page and lastUpdated as well as insert new data
                remoteKeyDao.insertKey(
                    RemoteKeyEntity(
                        id = KEY_USER_LIST,
                        nextSinceKey = nextSinceKey,
                        lastUpdated = System.currentTimeMillis(),
                    ),
                )
                userDao.insertUsers(userEntities)
            }

            return MediatorResult.Success(
                endOfPaginationReached = listUsers.isEmpty(),
            )
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    companion object {
        const val FIRST_SINCE = 0L
        const val DEFAULT_PAGE_SIZE = 20
        const val KEY_USER_LIST = "user_list"
    }
}
