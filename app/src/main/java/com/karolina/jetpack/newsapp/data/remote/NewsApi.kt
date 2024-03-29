package com.karolina.jetpack.newsapp.data.remote

import com.karolina.jetpack.newsapp.data.remote.dto.NewsResponse
import com.karolina.jetpack.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchString: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}