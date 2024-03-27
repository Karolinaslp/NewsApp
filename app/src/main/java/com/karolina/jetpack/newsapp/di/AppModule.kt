package com.karolina.jetpack.newsapp.di

import android.app.Application
import com.karolina.jetpack.newsapp.data.manager.LocalUserManagerImpl
import com.karolina.jetpack.newsapp.data.remote.NewsApi
import com.karolina.jetpack.newsapp.data.repository.NewsRepositoryImpl
import com.karolina.jetpack.newsapp.domain.manager.LocalUserManager
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.karolina.jetpack.newsapp.domain.usecases.news.GetNews
import com.karolina.jetpack.newsapp.domain.usecases.news.NewsUseCases
import com.karolina.jetpack.newsapp.domain.usecases.news.SearchNews
import com.karolina.jetpack.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    @Provides
    @Singleton
    fun provideUseAi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}