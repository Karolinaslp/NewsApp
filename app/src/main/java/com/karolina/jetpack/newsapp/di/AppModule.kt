package com.karolina.jetpack.newsapp.di

import android.app.Application
import com.karolina.jetpack.newsapp.data.manager.LocalUserManagerImpl
import com.karolina.jetpack.newsapp.domain.manager.LocalUserManager
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.SaveAppEntry
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
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

}