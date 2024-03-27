package com.karolina.jetpack.newsapp.presentation.search

import androidx.paging.PagingData
import com.karolina.jetpack.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {


}