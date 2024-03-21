package com.karolina.jetpack.newsapp.data.remote.dto

import com.karolina.jetpack.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)