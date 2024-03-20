package com.karolina.jetpack.newsapp.di

import android.app.Application
import com.karolina.jetpack.newsapp.data.manager.LocalUserManagerImpl
import com.karolina.jetpack.newsapp.domain.manager.LocalUserManager
import com.karolina.jetpack.newsapp.domain.usecases.AppEntryUseCases
import com.karolina.jetpack.newsapp.domain.usecases.ReadAppEntry
import com.karolina.jetpack.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}