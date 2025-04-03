package com.tyme.core.di

import com.tyme.core.dispatcher.DispatcherProvider
import com.tyme.core.dispatcher.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    fun providesDispatcher(): DispatcherProvider = DispatcherProviderImpl()
}
