package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.data.local.NewsDao
import com.karolina.jetpack.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles (private val newsDao: NewsDao) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}