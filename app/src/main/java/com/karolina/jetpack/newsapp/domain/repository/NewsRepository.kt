package com.karolina.jetpack.newsapp.domain.repository

import androidx.paging.PagingData

import com.karolina.jetpack.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}