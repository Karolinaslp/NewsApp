package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.data.local.NewsDao
import com.karolina.jetpack.newsapp.domain.model.Article

class DeleteArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }
}