package com.karolina.jetpack.newsapp.di

import android.app.Application
import androidx.room.Room
import com.karolina.jetpack.newsapp.data.local.NewsDao
import com.karolina.jetpack.newsapp.data.local.NewsDatabase
import com.karolina.jetpack.newsapp.data.local.NewsTypeConverter
import com.karolina.jetpack.newsapp.data.manager.LocalUserManagerImpl
import com.karolina.jetpack.newsapp.data.remote.NewsApi
import com.karolina.jetpack.newsapp.data.repository.NewsRepositoryImpl
import com.karolina.jetpack.newsapp.domain.manager.LocalUserManager
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.karolina.jetpack.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.karolina.jetpack.newsapp.domain.usecases.news.DeleteArticle
import com.karolina.jetpack.newsapp.domain.usecases.news.GetNews
import com.karolina.jetpack.newsapp.domain.usecases.news.NewsUseCases
import com.karolina.jetpack.newsapp.domain.usecases.news.SearchNews
import com.karolina.jetpack.newsapp.domain.usecases.news.SelectArticle
import com.karolina.jetpack.newsapp.domain.usecases.news.SelectArticles
import com.karolina.jetpack.newsapp.domain.usecases.news.UpsertArticle
import com.karolina.jetpack.newsapp.util.Constants.BASE_URL
import com.karolina.jetpack.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            selectArticle = SelectArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}