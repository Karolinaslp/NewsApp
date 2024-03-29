package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}