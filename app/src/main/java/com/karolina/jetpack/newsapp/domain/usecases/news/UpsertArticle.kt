package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository

class UpsertArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}