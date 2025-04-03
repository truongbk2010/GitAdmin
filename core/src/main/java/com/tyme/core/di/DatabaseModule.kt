package com.tyme.core.di

import android.content.Context
import androidx.room.Room
import com.tyme.core.local.AppDatabase
import com.tyme.core.local.RemoteKeyDao
import com.tyme.core.local.UserDao
import com.tyme.core.local.UserDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room
            .databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app.db",
            ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideUserDetailDao(database: AppDatabase): UserDetailDao = database.userDetailDao()

    @Provides
    fun provideRemoteKeyDao(database: AppDatabase): RemoteKeyDao = database.remoteKeyDao()
}
