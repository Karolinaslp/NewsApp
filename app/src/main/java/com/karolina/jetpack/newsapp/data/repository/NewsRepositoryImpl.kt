package com.karolina.jetpack.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.karolina.jetpack.newsapp.data.local.NewsDao
import com.karolina.jetpack.newsapp.data.remote.NewsApi
import com.karolina.jetpack.newsapp.data.remote.NewsPagingSource
import com.karolina.jetpack.newsapp.data.remote.SearchNewsPagingSource
import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
               SearchNewsPagingSource(
                   searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
       return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}