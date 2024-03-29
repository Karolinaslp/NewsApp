package com.karolina.jetpack.newsapp.domain.usecases.news

import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository

class SelectArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String): Article? {
       return newsRepository.selectArticle(url)
    }
}