package com.tyme.core.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tyme.core.dispatcher.DispatcherProvider
import com.tyme.core.helper.AppResult
import com.tyme.core.helper.toNetworkError
import com.tyme.core.local.AppDatabase
import com.tyme.core.local.RemoteKeyDao
import com.tyme.core.local.UserDao
import com.tyme.core.local.UserDetailDao
import com.tyme.core.mapper.toEntity
import com.tyme.core.mapper.toModel
import com.tyme.core.model.UserDetailModel
import com.tyme.core.model.UserModel
import com.tyme.core.network.UserService
import com.tyme.core.paging.UserPagingRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface UserRepository {

    fun getPagingSourceUser(): Flow<PagingData<UserModel>>

    fun getUserDetailStream(userId: Long): Flow<UserDetailModel?>

    suspend fun fetchUserDetail(userName: String): AppResult<UserDetailModel>

}

class UserRepositoryImpl
@Inject constructor(
    private val appDatabase: AppDatabase,
    private val userService: UserService,
    private val userDao: UserDao,
    private val userDetailDao: UserDetailDao,
    private val remoteDao: RemoteKeyDao,
    private val dispatcherProvider: DispatcherProvider,
) : UserRepository {


    @OptIn(ExperimentalPagingApi::class)
    override fun getPagingSourceUser(): Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = false,
        ),
        remoteMediator = UserPagingRemoteMediator(
            database = appDatabase,
            userService = userService,
            userDao = userDao,
            remoteKeyDao = remoteDao,
        ),
    ) {
        userDao.getUserPagingSource()
    }.flow.map { pagingData ->
        pagingData.map { it.toModel() }
    }.flowOn(dispatcherProvider.io())

    override fun getUserDetailStream(userId: Long): Flow<UserDetailModel?> {
        return userDetailDao.getUserDetailStream(userId).map { it?.toModel() }
            .flowOn(dispatcherProvider.io())
    }

    override suspend fun fetchUserDetail(userName: String) = withContext(dispatcherProvider.io()) {
        try {
            val userEntity = userService.getUserDetail(userName).toEntity()
            userDetailDao.insertUser(userEntity)
            AppResult.Success(userEntity.toModel())
        } catch (e: Exception) {
            AppResult.Error(e.toNetworkError())
        }
    }


}
