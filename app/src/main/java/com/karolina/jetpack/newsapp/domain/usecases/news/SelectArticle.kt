package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.data.local.NewsDao
import com.karolina.jetpack.newsapp.domain.model.Article

class SelectArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(url: String): Article? {
       return newsDao.getArticle(url)
    }
}