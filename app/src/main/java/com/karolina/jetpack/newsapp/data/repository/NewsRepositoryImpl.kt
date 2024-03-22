package com.karolina.jetpack.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.karolina.jetpack.newsapp.data.remote.NewsApi
import com.karolina.jetpack.newsapp.data.remote.NewsPagingSource
import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                    country = "pl",
                    category = "entertainment"
                )
            }
        ).flow
    }
}