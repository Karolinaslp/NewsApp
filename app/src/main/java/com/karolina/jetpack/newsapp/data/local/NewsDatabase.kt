package com.karolina.jetpack.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karolina.jetpack.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract val newsDao: NewsDao
}