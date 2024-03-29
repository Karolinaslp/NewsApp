package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.data.local.NewsDao
import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository

class DeleteArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}