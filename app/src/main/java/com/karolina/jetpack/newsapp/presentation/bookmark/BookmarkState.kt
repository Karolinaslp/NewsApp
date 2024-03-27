package com.karolina.jetpack.newsapp.presentation.bookmark

import com.karolina.jetpack.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
